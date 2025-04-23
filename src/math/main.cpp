//找出不大于n的最大质数
#include <stdbool.h>
#include <math.h>
#include <stdio.h>
#include <string.h>

void print_combinations(char* prefix, int prefix_len, char start_char) {
    for (char c = start_char; c <= 'z'; c++) {
        // 打印当前组合
        prefix[prefix_len] = c;
        prefix[prefix_len + 1] = '\0';
        printf("%s\n", prefix);
        
        // 递归处理更长的组合
        if (c < 'z') {
            print_combinations(prefix, prefix_len + 1, c + 1);
        }
    }
}

void all_letter_combinations() {
    char buffer[27] = {0}; // 最大26个字母+null终止符
    print_combinations(buffer, 0, 'a');
}
bool is_prime(int num) {
    if (num <= 1) return false;
    if (num == 2) return true;
    if (num % 2 == 0) return false;
    
    int sqrt_num = sqrt(num) + 1;
    for (int i = 3; i <= sqrt_num; i += 2) {
        if (num % i == 0) return false;
    }
    return true;
}

int largest_prime_below_n(int n) {
    if (n < 2) return -1; // 没有质数
    
    for (int i = n; i >= 2; i--) {
        if (is_prime(i)) return i;
    }
    
    return -1; // 不应该执行到这里
}
//找出1000个数中重复的数
int find_duplicate(int* nums, int size) {
    if (!nums || size != 1000) return -1;
    
    int seen[1000] = {0};
    for (int i = 0; i < size; i++) {
        if (nums[i] < 0 || nums[i] >= 1000) return -1; // 输入无效
        if (seen[nums[i]]++) return nums[i];
    }
    
    return -1; // 没有找到重复
}
//约瑟夫环问题
int last_remaining(int n, int m) {
    if (n < 1 || m < 1) return -1;
    
    int last = 0;
    for (int i = 2; i <= n; i++) {
        last = (last + m) % i;
    }
    return last + 1; // 转换为1-based编号
}
//找出所有字母组合


void print_combinations(char* prefix, int prefix_len, char start_char) {
    for (char c = start_char; c <= 'z'; c++) {
        // 打印当前组合
        prefix[prefix_len] = c;
        prefix[prefix_len + 1] = '\0';
        printf("%s\n", prefix);
        
        // 递归处理更长的组合
        if (c < 'z') {
            print_combinations(prefix, prefix_len + 1, c + 1);
        }
    }
}

void all_letter_combinations() {
    char buffer[27] = {0}; // 最大26个字母+null终止符
    print_combinations(buffer, 0, 'a');
}
int main() {
    // // 测试字符串分割
    // const char* str = "ab&&2";
    // const char* delimiter = "&&";
    // int count;
    // char** parts = split_string(str, delimiter, &count);
    
    // printf("Split result:\n");
    // for (int i = 0; i < count; i++) {
    //     printf("%s\n", parts[i]);
    //     free(parts[i]);
    // }
    // free(parts);
    
    // // 测试字符串组合
    // char* test_strings[] = {"ab", "2"};
    // char* combined = join_strings(test_strings, 2, "&&");
    // printf("Combined: %s\n", combined);
    // free(combined);
    
    // 测试最大质数
    printf("Largest prime below 100: %d\n", largest_prime_below_n(100));
    
    // 测试重复数字
    int nums[1000];
    for (int i = 0; i < 1000; i++) nums[i] = i;
    nums[500] = 499; // 设置重复
    printf("Duplicate number: %d\n", find_duplicate(nums, 1000));
    
    // 测试约瑟夫环
    printf("Last remaining (n=3, m=4): %d\n", last_remaining(3, 4));
    
    // 测试字母组合
    printf("Letter combinations:\n");
    all_letter_combinations();
    
    return 0;
}