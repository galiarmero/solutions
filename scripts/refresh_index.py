import os
import re
from collections import defaultdict, OrderedDict
import jinja2


def main():
    platforms = { 'leetcode': 'LeetCode' }
    ordered_categories = ['array', 'map', 'set', 'stack', 'string', 'dynamic programming']
    by_platform = _get_solutions_by_platform(platforms)
    by_category = _sort_by_category(by_platform, ordered_categories)
    template = _load_template('README.j2')
    new_readme = template.render(
                    by_platform=by_platform,
                    by_category=by_category,
                    platforms=platforms,
                )
    _write('README.md', new_readme)
    print("✅ Updated README")


def _get_solutions_by_platform(platforms):
    solutions = {}
    for platform in platforms:
        solutions[platform] = []
        for it in os.scandir(platform):
            if it.is_dir():
                readme = _read(os.path.join(it.path, 'README.md'))
                problem_id = _get_problem_id(it.path)
                title, link = _get_title_and_link(readme)
                categories = _get_categories(readme)
                solutions[platform].append({
                    'problem_id': int(problem_id),
                    'title': title,
                    'path': f'./{platform}/{os.path.basename(it.path)}',
                    'link': link,
                    'categories': categories,
                    'platform': platform,
                })
        solutions[platform].sort(key=lambda s: s['problem_id'])
    return solutions


def _sort_by_category(solutions_by_platform, ordered_cats):
    by_category = defaultdict(list)
    for platform, solutions in solutions_by_platform.items():
        for s in solutions:
            if not s['categories']:
                by_category['none'].append(s)
            else:
                for t in s['categories']:
                    by_category[t].append(s)
    by_category = OrderedDict(sorted(by_category.items(),
                key=lambda item: ordered_cats.index(item[0]) if item[0] in ordered_cats else len(ordered_cats)))
    return by_category


def _get_problem_id(path):
    match = re.findall('^(\d*?)-.*', os.path.basename(path))
    if match:
        return match[0]
    return None


def _get_title_and_link(readme):
    match = re.findall('^# \[(.*?)\]\((.*?)\)\n^', readme, re.DOTALL|re.MULTILINE)
    if match:
        return match[0]
    return None, None


def _get_categories(readme):
    match = re.findall('^---\ncategories: (.*?)\n^---', readme, re.DOTALL|re.MULTILINE)
    if match:
        return match[0].split(',')
    return None


def _load_template(filepath):
    loader = jinja2.FileSystemLoader(searchpath="./")
    env = jinja2.Environment(loader=loader, trim_blocks=True, lstrip_blocks=True)
    return env.get_template(filepath)


def _write(filepath, content):
    with open(filepath, "w") as f:
        f.write(content)


def _read(filepath):
    with open(filepath) as f:
        contents = f.read()
    return contents


if __name__ == "__main__":
    main()
