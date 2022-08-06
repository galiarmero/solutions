import os
import re
from collections import defaultdict, OrderedDict
import jinja2


def main():
    platforms = { 'leetcode': 'LeetCode' }
    ordered_categories = ['array', 'map', 'set', 'string', 'dynamic programming']
    by_platform = _get_solutions_by_platform(platforms)
    by_topic = _sort_by_topic(by_platform, ordered_categories)
    template = _load_template('README.j2')
    new_readme = template.render(
                    by_platform=by_platform,
                    by_topic=by_topic,
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
                topics = _get_topics(readme)
                solutions[platform].append({
                    'problem_id': int(problem_id),
                    'title': title,
                    'path': f'./{platform}/{os.path.basename(it.path)}',
                    'link': link,
                    'topics': topics,
                    'platform': platform,
                })
        solutions[platform].sort(key=lambda s: s['problem_id'])
    return solutions


def _sort_by_topic(solutions_by_platform, ordered_cats):
    by_topic = defaultdict(list)
    for platform, solutions in solutions_by_platform.items():
        for s in solutions:
            if not s['topics']:
                by_topic['none'].append(s)
            else:
                for t in s['topics']:
                    by_topic[t].append(s)
    by_topic = OrderedDict(sorted(by_topic.items(),
                key=lambda item: ordered_cats.index(item[0]) if item[0] in ordered_cats else len(ordered_cats)))
    return by_topic


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


def _get_topics(readme):
    match = re.findall('^---\ntopics: (.*?)\n^---', readme, re.DOTALL|re.MULTILINE)
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
