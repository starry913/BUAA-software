# MIPS
## 1.最大公约数
```c
#include <stdio.h>

int main() {
    int a, b, r;
    // 输入两个整数
    printf("请输入两个整数：");
    scanf("%d %d", &a, &b);

    // 欧几里得算法求最大公约数
    r = a % b;            // 初次取余
    while (r != 0) {      // 余数不为0，继续循环
        a = b;
        b = r;
        r = a % b;
    }

    // 输出最大公约数
    printf("最大公约数是：%d\n", b);

    return 0;
}
```
```py
.text
main:
    li $v0, 5       # $v0⬅5，表示读取整数 
    syscall         # 执行系统调用，结果放在v0
    move $s1, $v0   # t0⬅v0
    
    li $v0, 5       # $v0⬅5，表示读取整数 
    syscall         # 执行系统调用，结果放在v0
    move $s2, $v0   # t0⬅v0
    
    li $t0, 0
    li $t1, 0
    
    div $s1, $s2
    mfhi $t1
    
    bgt $t1, $t0, loop
    j output
    
loop:
    move $s1, $s2
    move $s2, $t1
    
    div $s1, $s2
    mfhi $t1
    bgt $t1, $t0, loop
    j output
    
output:
    li $v0, 1       # $v0⬅1，表示打印整数
    move $a0,$s2
    syscall 
    
end:
    li $v0, 10      # $v0⬅10，表示退出程序
    syscall         # 执行系统调用
```
## 2.素因数个数
```c
#include <stdio.h>
int main() 
{
    int n;
    scanf("%d", &n);
    int cnt = 0;
    for (int i = 2; i <= n; i++) 
    {
        if (n % i == 0) 
        {
            int flag = 1;
            for (int j = 2; j * j <= i; j++) 
            {
                if (i % j == 0) 
                {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1) 
            {
                while (n % i == 0)
                    n /= i;
                cnt++;
            }
        }
    }
    printf("%d\n", cnt);
    return 0;
}
```
t1 i
t2 j
t3 n%i
t4 flag
t5 j*j
t6 i%j
t7 1

s0 n
s1 cnt


```py
.text
main:
    li $v0, 5       # $v0⬅5，表示读取整数 
    syscall         # 执行系统调用，结果放在v0
    move $s0, $v0   # t0⬅v0
    
    li $s1,0
    li $t1,2

loop1:
    bgt $t1, $s0, print #i>n时
    div $s0, $t1
    mfhi $t3
    bne $t3, $zero, loop1_end
    
    li $t4,1
    li $t2,2
    

loop2:
    mul $t5, $t2, $t2
    bgt $t5, $t1, flag1
    
    div $t1, $t2
    mfhi $t6
    bne $t6, $zero, loop2_end
    li $t4,0
    j flag1
    
loop2_end:
    addi $t2,$t2,1
    j loop2

flag1:
    li $t7,1
    bne $t4, $t7, loop1_end
    addi $s1,$s1,1

loop3:
    div $s0, $t1
    mfhi $t3
    bne $t3, $zero, loop1_end
    
    div $s0, $t1       # $t0 / $t1
    mflo $s0           # t4为商
    j loop3
    
loop1_end:
    addi $t1,$t1,1
    j loop1

print:
    li $v0, 1       # $v0⬅1，表示打印整数
    move $a0,$s1
    syscall         # 执行系统调用

end:
    li $v0, 10      # $v0⬅10，表示退出程序
    syscall         # 执行系统调用
    
```
## 3.高精度阶乘
```c
#include <stdio.h>
#define length (500)

int a[length] = {1};

int main() {
    int n;
    scanf("%d", &n);
    for (int i = 2; i <= n; i++) {
        int jinwei = 0;
        for (int j = 0; j < length; j++) {
            int temp = a[j] * i + jinwei;
            jinwei = temp / 10;
            a[j] = temp % 10;
        }
    }
    int k = length - 1;
    while (!a[k]) { 
        k--;
    }
    while (k >= 0) 
        printf("%d", a[k--]);
    return 0;
}
```
```py
.data
array: .space 500  # 分配500个字节的空间，每个字节存储一个数字

.text
main:
    li $t0,1
    li $t1,1
    li $t2,500
    sb $t0, array   # array[0] = 1

begin:
    beq $t1, $t2, input
    sb $zero, array($t1)   # array[index] = 0
    addi $t1,$t1,1
    j begin

input:
    li $v0, 5       # $v0⬅5，表示读取整数 
    syscall         # 执行系统调用，结果放在v0
    move $t3, $v0
    
    li $t4,2

loop_i:
    bgt $t4, $t3, zero_erase_pre #if(i>n)
    li $t5,0
    li $t1,0
    
loop_j:
    bge $t1, $t2, loop_i_done #if(j>=length)
    lb $t6, array($t1)
    mul $t6, $t6, $t4
    add $t6, $t6, $t5
    
    li $t0,10
    div $t6, $t0       # $t0 / $t1
    mflo $t5           # t5为商
    mfhi $t7           # t7为余数
    
    sb $t7, array($t1)   # array[index] = t7
 
    addi $t1,$t1,1
    j loop_j
    
loop_i_done:
    addi $t4,$t4,1
    j loop_i
    
    
zero_erase_pre:
    li $t2,499
    
zero_erase:
    lb $t7, array($t2)
    bne $t7, $zero, print
    li $t0,1
    sub $t2, $t2, $t0
    j zero_erase
    
    
print:
    blt $t2, $zero, end
    lb $t7, array($t2)
    li $v0, 1       # $v0⬅1，表示打印整数
    move $a0, $t7     # a0⬅sum
    syscall         # 执行系统调用
    sub $t2, $t2, $t0
    j print
    
end:
    li $v0, 10      # $v0⬅10，表示退出程序
    syscall         # 执行系统调用
```
## 4.字符串部分逆置
```c
#include <stdio.h>
#include <string.h>

int main() {
    int n, t1, t2;
    char string[1001];
    // 输入字符串长度 n
    scanf("%d", &n);
    // 输入起始下标 t1
    scanf("%d", &t1);
    // 输入结束下标 t2
    scanf("%d", &t2);
    // 读取字符串，允许包含换行前的所有字符
    getchar(); // 吃掉上一行多余的换行
    fgets(string, n + 1, stdin);  // 字符串长度允许 n + '\0'
    // 反转 string[t1 .. t2]
    while (t1 < t2) {
        char temp = string[t1];
        string[t1] = string[t2];
        string[t2] = temp;
        t1++;
        t2--;

    // 输出结果
    printf("%s", string);

    return 0;
}

```
```py
.data
string: .space 1001        # 预留 1001 字节存储字符串（含 '\0'）

.text
main:
    # ===== 输入字符串长度 n 到 t0 =====
    li $v0, 5              # 系统调用5：读取整数
    syscall
    move $t0, $v0          # t0 = n（用户输入的字符串长度）
    
    # ===== 输入起始下标 t1 =====
    li $v0, 5
    syscall
    move $t1, $v0          # t1 = 起始下标
    
    # ===== 输入结束下标 t2 =====
    li $v0, 5
    syscall
    move $t2, $v0          # t2 = 结束下标
    
    # ===== 输入字符串 =====
    li $v0, 8              # 系统调用8：读取字符串
    la  $a0, string        # $a0 = string 数组的首地址
    move $a1, $t0          # $a1 = 字符串最大长度
    addi $a1, $a1, 1       # 允许字符串包含换行符 '\n'
    syscall                # 将用户输入写入 string
    
# ================== 字符交换循环 ==================
# 将 string[t1] 与 string[t2] 对换，然后下标递进
loop_reverse:
    bge $t1, $t2, output   # 若 t1 >= t2，说明交换完毕 → 跳到 output
    
    lb $t3, string($t1) # 读出 string[t1] 到 t3
    lb $t4, string($t2) # 读出 string[t2] 到 t4
    
    sb $t3, string($t2) # 将 t3（原 string[t1]）写到 string[t2]
    sb $t4, string($t1) # 将 t4（原 string[t2]）写到 string[t1]
    
    addi $t1, $t1, 1  # 左指针右移
    subi $t2, $t2, 1  # 右指针左移
    j loop_reverse         # 继续循环
    
# ================== 输出处理后的字符串 ==================
output:
    li $v0, 4              # 系统调用4：打印字符串
    la $a0, string         # 加载 string 地址
    syscall
    
# ================== 程序结束 ==================
end:
    li $v0, 10             # 系统调用10：退出程序
    syscall

```

## 5.汉诺塔递归
```c
#include <stdio.h>

void printMove(char from, char to)
 {
    printf("%c->%c\n", from, to);
}

void hanoi(int n, char from, char temp, char to) 
{
    if (n == 1) 
    {
        printMove(from, to);
        return;
    }
    hanoi(n - 1, from, to, temp);
    printMove(from, to);
    hanoi(n - 1, temp, from, to);
}

int main() 
{
    int n;
    printf("请输入盘子数量: ");
    scanf("%d", &n);
    hanoi(n, 'A', 'B', 'C');
    return 0;
}
```
```py
.data
newline: .asciiz "\n"
arrow: .asciiz "->"    # 输出 “->” 使用

.text
main:
    # 读取用户输入的圆盘数量 n
    li $v0, 5
    syscall
    move $s0, $v0       # $s0 保存圆盘数

    # 设置 hanoi(n, 'A', 'B', 'C')
    move $a0, $s0       # 参数 n = 用户输入
    li $a1, 'A'         # 起始柱 from = A
    li $a2, 'B'         # 辅助柱 temp = B
    li $a3, 'C'         # 目标柱 to   = C
    jal hanoi           # 调用递归汉诺塔函数

    # 正常结束程序
    li $v0, 10
    syscall


##########################################################
# hanoi(n, from, temp, to)
#
# 思想：
# if n == 1:
#     打印 from -> to
# else:
#     hanoi(n-1, from, to, temp)
#     打印 from -> to
#     hanoi(n-1, temp, from, to)
##########################################################
hanoi:
    # 为本次函数调用分配栈空间(20字节)
    addi $sp,$sp,-20

    # 存储调用环境
    sw $ra,16($sp)      # 返回地址
    sw $a0,12($sp)      # 参数 n
    sw $a1,8($sp)       # 参数 from
    sw $a2,4($sp)       # 参数 temp
    sw $a3,0($sp)       # 参数 to

    # 判断是否为递归基（n == 1）
    li $t0,1
    beq $a0,$t0,BaseCase


######## 第一部分递归：移动 n-1 个盘子到辅助位置 ########
    # hanoi(n-1, from, to, temp)
    addi $a0,$a0,-1     # n-1
    lw $t1,0($sp)       # 取 to
    lw $t2,4($sp)       # 取 temp
    move $a2,$t1        # 新 temp = 原 to
    move $a3,$t2        # 新 to   = 原 temp
    jal hanoi


######## 打印移动操作 ########
    # 取回原始参数数据
    lw $a0,12($sp)
    lw $a1,8($sp)
    lw $a2,4($sp)
    lw $a3,0($sp)

    jal print           # 打印 from -> to


######## 第二部分递归：移动剩余盘子 ########
    # hanoi(n-1, temp, from, to)
    lw $a0,12($sp)
    addi $a0,$a0,-1
    lw $t1,4($sp)       # temp
    lw $t2,8($sp)       # from
    lw $t3,0($sp)       # to
    move $a1,$t1        # 新 from = 原 temp
    move $a2,$t2        # 新 temp = 原 from
    move $a3,$t3        # to 不变
    jal hanoi

    j Exit              # 完成本层递归，跳转恢复栈并返回


######## 递归基：只移动一个盘子 ########
BaseCase:
    # 恢复正确的柱子参数（因为 print 需要）
    lw $a1,8($sp)
    lw $a2,4($sp)
    lw $a3,0($sp)
    jal print           # 输出一次移动


######## 统一返回出口 ########
Exit:
    lw $ra,16($sp)      # 恢复返回地址
    addi $sp,$sp,20     # 释放当前栈帧(每一层对应释放一次)
    jr $ra              # 返回上一层


##########################################################
# print(from, to) —— 输出一次盘子移动
##########################################################
print:
    # 保护参数，避免破坏 hanoi 层的数据
    addi $sp,$sp,-16
    sw $a0,12($sp)
    sw $a1,8($sp)
    sw $a2,4($sp)
    sw $a3,0($sp)

    # 输出 from 字符
    li $v0,11
    move $a0,$a1
    syscall

    # 输出 ->
    li $v0,4
    la $a0,arrow
    syscall

    # 输出 to 字符
    li $v0,11
    move $a0,$a3
    syscall

    # 换行
    li $v0,4
    la $a0,newline
    syscall

    # 恢复参数
    lw $a0,12($sp)
    lw $a1,8($sp)
    lw $a2,4($sp)
    lw $a3,0($sp)
    addi $sp,$sp,16
    jr $ra

```