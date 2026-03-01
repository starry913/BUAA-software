# STL竞赛函数
```cpp
#include <bits/stdc++.h>
using namespace std;
```
## 1.pair(一对值的组合)
```cpp
std::pair<int,double> p1(1,3.14);
std::pair<char,std::string> p2('a',"hello");
std:: cout<<p1.first<<", "<<p1.second<< std::endl; //1, 3.14
std:: cout<<p2.first<<", "<<p2.second<< std::endl; //a, hello
```

## 2.vector动态数组
```cpp
std::vector<int> vec;//下标从0开始
//元素访问
vec[0]//索引访问，从0开始

//元素添加删除
vec.push_back(5);//在末尾添加元素5
vec.pop_back();//删除末尾元素
vec.insert(vec.begin()+2,3);//vec[2]=3;后面依次往后移
vec.erase(vec.begin()+2);//删除vec[2],后面的依次前移
vec.clear();//清空向量

//大小管理
vec.size();//获取数量
vec.empty();//是否为空
resize();//调整vector的大小

//迭代器
begin();//获取第一个元素的迭代器
end();//获取指向最后一个元素之后位置的迭代器
for(auto it=vec.begin();it!=vec.end();it++)    //遍历输出vector
{
    std::cout<< *it <<" ";
}
for(const auto& num : vec)        //遍历输出vector
{
    std::cout<< num <<" ";
}

//排序去重
std::sort(vec.begin(),vec.end());
auto last= std::unique(vec.begin(),vec.end());//把相邻重复元素移植末尾last
vec.erase(last,vec.end());
```
## 3.queue动态队列
```cpp
queue<int> q;
push(x);    //队尾插入x
pop();      //弹出队首元素
front();    //返回队首元素
back();     //返回队尾元素
empty();    //是否为空
size();     //返回元素个数

priority_queue   //优先队列(堆)(队首一定是最大值)
push(x);    //将x插入到优先队列中
pop();      //弹出优先队列的顶部元素
top();      //返回优先队列的顶部元素
empty();    //是否为空
size();     //返回元素个数

//法一：从小到大排
priority_queue<int, vector<int>, greater<int>> q;//小顶堆
priority_queue<int, vector<int>, less<int>> q;//大顶堆
//法二：复杂排序
struct Compare
{
    bool operator()(int a,int b)
    {
        //自定义比较函数，按照逆序排列
        return a>b;
    }
};
priority_queue<int, vector<int>, Compare> pq;



```



