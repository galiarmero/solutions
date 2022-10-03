import os
import re
import sys
import requests


def main(url):
    if 'leetcode' in url:
        problem = _get_leetcode_problem_info(url)
    
    path = os.path.join(problem['platform'], problem['dir_name'])
    _make_dir(path)
    _create_readme(path, problem)
    _create_source_code(path, problem)


def _get_leetcode_problem_info(url):
    slug = re.search('https://leetcode.com/problems/(.*?)((\/.*$)|$)', url).group(1)
    payload = {  
        'operationName': 'questionData',
        'variables': {
            'titleSlug': slug
        },
        'query': """query questionData($titleSlug: String!) {
            question(titleSlug: $titleSlug) {
                questionFrontendId
                title
                titleSlug
                codeSnippets {
                    lang
                    langSlug
                    code
                }
            }
        }
        """,
    }
    res = requests.post('https://leetcode.com/graphql/', json=payload)
    question = res.json()['data']['question']
    return {
        'platform': 'leetcode',
        'url': url,
        'problem_id': question['questionFrontendId'],
        'dir_name': f"{question['questionFrontendId']}-{question['titleSlug']}",
        'title': question['title'],
        'snippet': [ c['code'] for c in question['codeSnippets'] if c['langSlug'] == 'java' ][0],
    }


def _create_readme(path, problem):
    _write(os.path.join(path, 'README.md'), f"""---
categories: 
---

# [{problem['title']}]({problem['url']})

Explanation is pending. For now, you can check the solution:

- [Solution.java](./Solution.java)

""")
    print(f"✅ Created README.md in {path}")


def _create_source_code(path, problem):
    _write(os.path.join(path, 'Solution.java'), problem['snippet'] + '\n')
    print(f"✅ Created Solution.java in {path}")


def _write(filepath, content):
    with open(filepath, "w") as f:
        f.write(content)


def _make_dir(path):
    if not os.path.exists(path):
        os.makedirs(path)
        print(f"✅ Created directory {path}")
    else:
        print(f"❌ {path} already exists")


if __name__ == "__main__":
    try:
        main(sys.argv[1])
    except IndexError:
        print("❌ No problem URL argument provided. Try `new_solution.py https://leetcode.com/problems/{problem-path}/`")
