# 复杂STL
## 1.哈希表(unordered_map)
### 1.1基本操作
```cpp
#include <bits/stdc++.h>
using namespace std;
int main() {
    unordered_map<string, int> ump;
    // 1. 插入数据
    ump["apple"] = 5;
    ump["banana"] = 3;
    ump["orange"] = 8;
    // 2. 访问数据
    cout << "apple: " << ump["apple"] << endl;
    // 3. 修改数据
    ump["apple"] = 10;
    // 4. 删除数据
    ump.erase("banana");
    return 0;
}
```
### 1.2查找
```cpp
#include <bits/stdc++.h>
using namespace std;

int main() {
    unordered_map<string, int> ump;
    ump["apple"] = 5;
    ump["banana"] = 3;
    // 方法1：使用find()函数
    if (ump.find("apple") != ump.end()) {
        cout << "找到了apple: " << ump["apple"] << endl;
    } else {
        cout << "没找到apple" << endl;
    }
    // 方法2：使用count()函数
    if (ump.count("banana") > 0) {
        cout << "找到了banana: " << ump["banana"] << endl;
    }
    // 方法3：直接访问（如果不存在会创建）
    cout << "orange: " << ump["orange"] << endl;  // 输出0（默认值）
    
    return 0;
}
```
### 1.3遍历
```cpp
#include <bits/stdc++.h>
using namespace std;

int main() {
    unordered_map<string, int> ump;
    ump["apple"] = 5;
    ump["banana"] = 3;
    ump["orange"] = 8;
    
    // 方法1：范围for循环
    cout << "方法1 - 范围for循环：" << endl;
    for (auto &ele : ump) {
        cout << ele.first << ": " << ele.second << endl;
    }
    // 方法2：迭代器
    cout << "方法2 - 迭代器：" << endl;
    for (auto it = ump.begin(); it != ump.end(); it++) {
        cout << it->first << ": " << it->second << endl;
    }
    // 方法3：只遍历键
    cout << "方法3 - 只遍历键：" << endl;
    for (auto &key : ump) {
        cout << key.first << endl;
    }
    
    return 0;
}
```

### 1.4统计
```cpp
//读入多个单词，找哪个单词出现了5次
#include <bits/stdc++.h>
using namespace std;
int main() 
{
    int n; cin >> n;
    unordered_map<string, int> ump;
    while (n--) 
    {
        string s; cin >> s;
        ump[s]++;
    }
    for (auto &ele : ump) 
    {
        if (ele.second == 5) cout << ele.first << endl;
    }
    return 0;
}
```