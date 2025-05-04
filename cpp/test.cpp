// 要求：使用c++语言，熟悉UE者可以使用UE版c++，代码直接粘贴到每道题后面发回来，禁止使用AI，测试时长1小时



// 1.给定一个整数a，请编写一个函数，计算并返回以下数列的和： 1 + 2 + 3 + ... + a (a > 0 && a <= 2^32)
void series(int a)
{   
    if(a <= 0 || a > 4294967296)
    {
        cout << "数据有问题" << endl;
        return;
    }else
    {
        int sum=0;
        sum=((1+a)*a)/2;
        cout<<"sum :"<<sum<<endl;
    }


}
// int main()
// {
//     int a;
//     cin>>a;
//     series(a);

//     return 0;
// }
// 2.给定一个float数组，请编写一个函数，计算数组中出现次数最多的数字及其出现次数,如果出现次数最多的数字有多个，返回任意一个即可。(数组中的所有数字均为随机数生成并且保留一位小数)

void arr(vector v_array)
{
    float number=0.0;
    int count=0;
    unordered_map<float,int> m;//第一个是用于存储数字，第二个是用于存储数字出现的次数
    for(int i=0;i<v_array.size();i++)
    {
        m[v_array[i]]++;
        if(m[v_array[i]]>count)
        {
            count=m[v_array[i]];
            number=v_array[i];
        }
    }
}
// int main()
// {
//     int a;
//     cin>>a;//输入生成的随机数个数
//     vector<float> v_array;
//     srand((unsigned)time(NULL));
//     for(int i=0;i<a;i++)
//     {
//         float num=rand()%1000/10.0;
//         v_array.push_back(num);
//     }
//     cout<<"生成的随机数为：准备传入我们的arr进行一个哈希统计"<<endl;
//     arr(v_array);
//     return 0;
// }
// 3.给定一个从小到大排好序的整数数组，和一个需要查找的数字a，请编写一个函数,找到a时返回第一个a的位置,找不到时返回-1。（要求效率尽可能高）

int find_index(vector<int> v_array)
{   
    int number=0;
    cin>>number;
    for(int =0;i<v_array.size();i++)
    {
        if(v_array[i]==number)
        {
            return i;
        }
    }
    return -1;
}

int main()
{
    int a;
    vector<int> v_array;
    while(1)
    {
        cout<<"请输入一个数字："<<endl;
        cin>>a;
        if(a==0)
        {
            break;
        }
        v_array.push_back(a);
    }
    sort(v.array.begin(),v_array.end());//排序
    int index=find_index(v_array);

    return 0;
}
// 4.请设计一个回合制角色战斗系统，满足以下要求：

// 有两个角色战士(Warrior)和法师(Mage)，均包含角色名、血量(HP)、攻击力(ATK)、防御力(DEF)、暴击概率(CritRate)。

// 战士普通攻击伤害为ATK，拥有技能“重击”，发动后攻击力变为1.5倍，但下回合必须休息不能行动。

// 法师普通攻击伤害为ATK，拥有技能“火球”，对敌方造成ATK*2的伤害，技能释放后需等待1回合冷却才能再次使用。

// 每次行动时均有50%几率释放普通攻击和技能，如果随机到技能但是技能无法释放，则释放普通攻击

// 伤害为（攻击者攻击力 - 防御者防御力）
// 每次攻击有一定暴击概率（CritRate），暴击时伤害翻倍。

// 游戏循环为回合制，双方轮流攻击，直至一方死亡。

class Mage{//法师
public:
    //火球
    void firebool()
    {//法师普通攻击伤害为ATK，拥有技能“火球”，对敌方造成ATK*2的伤害，技能释放后需等待1回合冷却才能再次使用。
        if(rest)//在休息状态不能放
        {
            cout<<"不能攻击"<<endl;
            return;

        }
        float damage=ATK*2-target.DEF;
        if(rand()%100<CritRate*100)
        {
            damage*=2;
            cout<<"暴击"<<endl;
        }
        target.HP-=damage;
        cout<<name<<"对"<<target.name<<"造成了"<<damage<<"点伤害"<<endl;
        if(target.HP<=0)
        {
            cout<<target.name<<"被击败了"<<endl;
        }
        rest=true;//释放技能后进入休息状态

    }
    //正常攻击
    void attack(Mage& target)
    {
        float damage=ATK-target.DEF;
        if(damage<0)
        {
            damage=0;
        }
        if(rand()%100<CritRate*100)
        {
            damage*=2;
            cout<<"暴击"<<endl;
        }
        target.HP-=damage;
        cout<<name<<"对"<<target.name<<"造成了"<<damage<<"点伤害"<<endl;
        if(target.HP<=0)
        {
            cout<<target.name<<"被击败了"<<endl;
        }
    }
    //构造函数
    Mage(string name,float HP,float ATK,float DEF,float CritRate)
    {
        this->name=name;
        this->HP=HP;
        this->ATK=ATK;
        this->DEF=DEF;
        this->CritRate=CritRate;
        rest=false;
    }
    //析构函数
    ~Mage()
    {
        cout<<"法师"<<name<<"被击败了"<<endl;
    }
    //加入一个rest状态机
    void endTurn(){
        if(rest)
        {
            rest=false;
            cout<<name<<"休息结束"<<endl;
        }
    }
    bool isAlive()
    {
        return HP>0;
    }

    private:
    string name;
    float HP;
    float ATK;
    float DEF;
    float CritRate;
    bool rest;//休息
}

class Warrior{//战士
    //重伤
    void serious(Warrior& target)
    {//法师普通攻击伤害为ATK，拥有技能“火球”，对敌方造成ATK*2的伤害，技能释放后需等待1回合冷却才能再次使用。
        if(rest)//在休息状态不能放
        {
            cout<<"不能攻击"<<endl;
            
            return;

        }
        float damage=ATK*1.5-target.DEF;
        if(rand()%100<CritRate*100)
        {
            damage*=2;
            cout<<"暴击"<<endl;
        }
        target.HP-=damage;
        cout<<name<<"对"<<target.name<<"造成了"<<damage<<"点伤害"<<endl;
        if(target.HP<=0)
        {
            cout<<target.name<<"被击败了"<<endl;
        }
        rest=true;//释放技能后进入休息状态
    }
    //正常攻击
    void attack(Warrior& target)
    {
        if(rest)//在休息状态不能放
        {
            cout<<"不能攻击"<<endl;
            rest = false; // 结束休息
            return;

        }
        float damage = max(0.0f, ATK - target.DEF);
        if (rand() % 100 < CritRate * 100) { // 暴击
            damage *= 2;
            cout << name << " 触发暴击！" << endl;
        }
        target.HP -= damage;
        cout << name << " 对 " << target.name << " 造成了 " << damage << " 点伤害！" << endl;
        if (target.HP <= 0) {
            cout << target.name << " 被击败了！" << endl;
        }

    }
    // 判断是否存活
    bool isAlive() {
        return HP > 0;
    }
    //构造函数
    Mage(string name,float HP,float ATK,float DEF,float CritRate)
    {
        this->name=name;
        this->HP=HP;
        this->ATK=ATK;
        this->DEF=DEF;
        this->CritRate=CritRate;
        rest=false;
    }
    //析构函数
    ~Mage()
    {
        cout<<"战士"<<name<<"被击败了"<<endl;
    }

    private:
    string name;
    float HP;
    float ATK;
    float DEF;
    float CritRate;
    bool rest;//休息
};
void battle(Warrior& warrior, Mage& mage)
{
    srand(static_cast<unsigned>(time(0))); // 初始化随机数种子
    while (warrior.isAlive() && mage.isAlive()) {
        // 战士回合
        cout << "战士的回合：" << endl;
        if (rand() % 2 == 0) {
            warrior.heavyStrike(mage);
        } else {
            warrior.attack(mage);
        }
        if (!mage.isAlive()) {
            cout << "法师被击败了！战斗结束！" << endl;
            break;
        }

        // 法师回合
        cout << "法师的回合：" << endl;
        if (rand() % 2 == 0) {
            mage.fireball(warrior);
        } else {
            mage.attack(warrior);
        }
        if (!warrior.isAlive()) {
            cout << "战士被击败了！战斗结束！" << endl;
            break;
        }

        // 回合结束
        mage.endTurn();
        cout << "战士剩余HP：" << warrior.isAlive() << "法师剩余HP：" << mage.isAlive() << endl;
       
    }
}
int main()
{
    Warrior warrior("战士", 100, 20, 5, 0.5);
    Mage mage("法师", 80, 25, 3, 0.5);

    battle(warrior, mage);

    return 0;
}