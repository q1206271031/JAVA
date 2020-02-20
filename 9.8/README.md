## Java集合

> 集合类存放于 Java.util 包中，主要有 3 种：set(集）、list(列表包含 Queue）和 map(映射)

* Collection：Collection 是集合 List、Set、Queue 的最基本的接口。 
* Iterator：迭代器，可以通过迭代器遍历集合中的数据 

```java
List<String> list = new LinkedList<String>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("fourth");
        for(Iterator<String> iterator = list.iterator();iterator.hasNext();){
            String string = (String)iterator.next();
            System.out.println(string);
        }
```

* Map：是映射表的基础接口

### List

> List 是有序的 Collection。Java List 一共三个实现类： 
>
> 分别是 ***ArrayList***、***Vector*** 和 ***LinkedList***

#### ArrayList（数组）

ArrayList 是最常用的 List 实现类，内部是通过数组实现的，它允许对元素进行快速随机访问。数 

组的缺点是每个元素之间不能有间隔，当数组大小不满足时需要增加存储能力，就要将已经有数 

组的数据复制到新的存储空间中。当从 ArrayList 的中间位置插入或者删除元素时，需要对数组进 

行复制、移动、代价比较高。***因此，它适合随机查找和遍历，不适合插入和删除。***

#### **Vector**（数组实现、线程同步）

Vector 与 ArrayList 一样，也是通过数组实现的，不同的是它支持线程的同步，即某一时刻只有一 

个线程能够写 Vector，避免多线程同时写而引起的不一致性，但实现同步需要很高的花费，因此， 

访问它比访问 ArrayList 慢。

#### **LinkList**（链表）

LinkedList 是用链表结构存储数据的，很适合数据的动态插入和删除，随机访问和遍历速度比较 

慢。另外，他还提供了 List 接口中没有定义的方法，专门用于操作表头和表尾元素，可以当作堆 

栈、队列和双向队列使用。

### Set

> 用于存储无序(存入和取出的顺序不一定相同)元素，值不能重 复。对象的相等性本质是对象 hashCode 值（java 是依据对象的内存地址计算出的此序号），***如果想要让两个不同的对象视为相等的，就必须覆盖 Object 的 hashCode 方法和 equals 方法。***

#### **HashSet**（Hash表）

* 哈希表边存放的是哈希值, HashSet 首先判断两个元素的哈希值，如果哈希值一样，接着会比较 
* equals 方法 如果 equls 结果为 true ，HashSet 就视为同一个元素。如果 equals 为 false 就不是 同一个元素。
* 由HashMap实现

#### **TreeSet**（二叉树）

* TreeSet()是使用二叉树的原理对新 add()的对象按照指定的顺序排序（升序、降序），每增 

加一个对象都会进行排序，将对象插入的二叉树指定的位置。 

* Integer 和 String 对象都可以进行默认的 TreeSet 排序，而自定义类的对象是不可以的，自 
* 由TreeMap实现

己定义的类必须实现 Comparable 接口，并且覆写相应的 compareTo()函数，才可以正常使 

用。 

* 在覆写 compare()函数时，要返回相应的值才能使 TreeSet 按照一定的规则来排序 
* 比较此对象与指定对象的顺序。如果该对象小于、等于或大于指定对象，则分别返回负整 

数、零或正整数。

### **Map**

> 键值对存储

#### **HashMap（数组**+链表+红黑树）

HashMap 根据键的 hashCode 值存储数据，HashMap 最多只允许一条记录的键为 null，允许多条记 

录的值为 null。HashMap 非线程安全。如果需要满足线程安全，可以使用 ConcurrentHashMap

* capacity：当前数组容量，始终保持 2^n，可以扩容，扩容后数组大小为当前的 2 倍。 
* loadFactor：负载因子，默认为 0.75。

Java7查找时需要顺着链表一个个比较下去才能找到我们需要的，时间复杂度取决 

于链表的长度，为 O(n)

Java8 对 HashMap 进行了一些修改，最大的不同就是利用了红黑树，所以其由 数组+链表+红黑 

树组成当链表中的元素超过了 8 个以后， 会将链表转换为红黑树，在这些位置进行查找的时候可以降低时间复杂度为 O(logN)

#### **ConcurrentHashMap**

##### **Segment** **段**

支持并发操作,整个 ConcurrentHashMap 由一个个 Segment 组成，Segment 代表”部分“或”一段“的 

意思，所以很多地方都会将其描述为分段锁

##### **线程安全（**Segment **继承** **ReentrantLock** **加锁）**

ConcurrentHashMap 是一个 Segment 数组，Segment 通过继承 

ReentrantLock 来进行加锁，所以每次需要加锁的操作锁住的是一个 segment，这样只要保证每 

个 Segment 是线程安全的，也就实现了全局的线程安全。

#### **TreeMap**（可排序）

TreeMap 实现 SortedMap 接口，能够把它保存的记录根据键排序，默认是按键值的升序排序， 

也可以指定排序的比较器，当用 Iterator 遍历 TreeMap 时，得到的记录是排过序的

在使用 TreeMap 时，key 必须实现 Comparable 接口或者在构造 TreeMap 传入自定义的 

Comparator，否则会在运行时抛出 java.lang.ClassCastException 类型的异常

#### **LinkHashMap**（记录插入顺序）

LinkedHashMap 是 HashMap 的一个子类，保存了记录的插入顺序，在用 Iterator 遍历 

LinkedHashMap 时，先得到的记录肯定是先插入的，也可以在构造时带参数，按照访问次序排序

## 排序

|   类型   |         不同         | 时间复杂度 |  空间复杂度   |
| :------: | :------------------: | :--------: | :-----------: |
| 插入排序 | 只能针对数据在内存中 |   O(N^2)   |     O(1)      |
| 希尔排序 | 只能针对数据在内存中 |  O(N^1.3)  |               |
| 选择排序 | 只能针对数据在内存中 |   O(N^2)   |     O(1)      |
|  堆排序  | 只能针对数据在内存中 |  O(NlogN)  |     O(1)      |
| 冒泡排序 | 只能针对数据在内存中 |   O(N^2)   |     O(1)      |
| 快速排序 | 只能针对数据在内存中 |  O(NlogN)  | O(log2n)~O(n) |
| 归并排序 |   数据允许在外存中   |  O(NlogN)  |     O(n)      |

### 七大排序算法

#### 插入排序

一次从 桌上摸起一张牌，并将它插入到左手一把牌中的正确位置上。为了找到这张牌的正确位置，要将 它与手中已有的牌从右到左地进行较。无论什么时候，左手中的牌都是排好序的

```java
public static void insertSort(int[] arr){
        for(int bound = 1;bound < arr.length;bound++){
            int tmp = arr[bound];
            int cur = bound - 1;
            for(;cur >= 0;cur--){
                if(arr[cur] > tmp){
                    arr[cur + 1] = arr[cur];
                }else{
                    break;
                }
            }
            arr[cur + 1] = tmp;
        }
    }
```

#### 希尔排序

先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列 中的记录“基本有序”时，再对全体记录进行依次直接插入排序

```java
public static void shellSort(int[] arr){
        int gap = arr.length;
        while (gap > 1) {
            insertSortGap(arr,gap);
            gap /=2;
        }
        insertSortGap(arr,1);
    }
    private static void insertSortGap(int[] arr,int gap){
        for(int bound = 1;bound < arr.length;bound++){
            int tmp = arr[bound];
            int cur = bound - gap;
            for(;cur >= 0;cur--){
                if(arr[cur] > tmp){
                    arr[cur + gap] = arr[cur];
                }else{
                    break;
                }
            }
            arr[cur + gap] = tmp;
        }
    }
```

#### 选择排序

每一次从无序区间选出最大（或最小）的一个元素，存放在无序区间的最后（或最前），直到全部待排序的数据元素排完

```java
public static void selectSort(int[] arr){
        for(int i = 0;i < arr.length ;i++){
            // 无序区间: [0, array.length - i)
            // 有序区间: [array.length - i, array.length)
            for(int j = i + 1;j < arr.length;j++){
                if(arr[j] < arr[i]){
                    swap(arr,i,j);
                }
            }
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
```

#### 堆排序

***堆：***

* 完全二叉树 
* 使用线性方式存储(下标之间存在一些关联关系) 
* 任何一个子树之中，父节点比子节点大(大堆)

> 堆排序思路和直接排序很像,每次取出一个最大值，放在数组最

* 先针对整个数组建堆
* 把堆项元素放在数组最后
* 重新调整堆

```java
public static void heapSort(int[] array){
        //1.创建堆
        createHeap(array);
        //2.循环取出堆顶最大值，放在最后
        for (int i = 0; i < array.length; i++) {
            //待排序区间：【0，array.length - i）
            //已排序区间：【array.length - i,array.length)
            swap(array,0,array.length - i - 1);
            shiftDown(array,array.length - i - 1,0);
        }
    }

    public static void createHeap(int[] array){
        //从最后一个非叶子节点，开始从后出发，从后往前开始向下调整
        for(int i = (array.length - 1 - 1) / 2;i >= 0;i--){
            shiftDown(array,array.length,i);
        }
    }

    public static void shiftDown(int[] array,int size,int index){
        int parent = index;
        int child = 2 * parent + 1;
        while(child < size){
            if(child + 1 < size && array[child + 1]> array[child]){
                child = child + 1;
            }
            //经历了上面的if之后，child指向左右子树最大值
            if(array[child] > array[parent]){
                //建立大堆
                swap(array,child,parent);
            }else{
                break;
            }
            parent = child;

            child = 2 * parent + 1;
        }
    }
```

#### 冒泡排序

* 比较前后相邻的二个数据，如果前面数据大于后面的数据，就将这二个数据交换。 
* 这样对数组的第 0 个数据到 N-1 个数据进行一次遍历后，最大的一个数据就“沉”到数组第 N-1 个位置。 
* N=N-1，如果 N 不为 0 就重复前面二步，否则排序完成。

```java
public static void bubbleSort(int[] arr){
        for(int i = 0;i < arr.length - 1;i++){
            for(int j = 1;j < arr.length - i - 1;j++){
                if(arr[j] > arr[j + 1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }
```

#### 快速排序

> ```
> 先选择一个基准值（最后一个元素）
> 1.从前往后找到第一个比基准值大的元素
> 2.从后往前找到第一个比基准值小的元素
> 3.交换这两个位置的元素
> ```

```java
public static void quickSOrt(int[] array){
        quickSortHelper(array,0,array.length - 1);
    }
    public static void quickSortHelper(int[] array,int left,int right){
        //如果只有一个元素或者没有元素
        if(left >= right){
            return;
        }
        //这个方法就是区间整理的方法
        //选取基准值，并且把小于基准值的值放在左侧，大于的放在右侧
        //返回值[left,right]最终整理完毕后，基准值的下标
        int index = partition(array,left,right);
        quickSortHelper(array,left,index - 1);//左侧
        quickSortHelper(array,index + 1,right);//右侧
    }

    public static int partition(int[] array,int left,int right){
        //基准值
        int baseIndex = left;
        int baseValue = array[baseIndex];
        while(left < right){
            while(left < right && array[right] > baseValue){
                right--;
            }
            //循环结束，right指向的就是从右往左第一个比基准值小的元素
            while(left < right && array[left] <= baseValue){
                left++;
            }
            //循环结束，left指向的就是从左往右第一个比基准值大的元素
            //交换left和right位置的元素
            swap(array,left,right);
        }
        //当前这个代码是把left和right重合位置的元素和基准值交换
        //基准值是最后一个元素，要交换
        //前提是 left 和 right 重合位置对应的元素, 必须得比基准值大
        //循环结束两种情况：
        //1.left++导致的
        //    上次循环过程中, 进行了一个 swap 操作. 经过这个 swap 操作之后,
        //    right 一定指向一个大于基准值的元素. 此时如果 left 和 right 重合
        //    也一定是指向一个大于基准值的元素
        //2.right--导致的
        //    此时由于 left 刚刚找到一个比基准值大的元素, 此时 right 和 left
        //    重合之后, 对应的元素也就是刚才的那个比基准值大的值
        swap(array,left,baseIndex);
        return left;
    }
```

> 非递归

```java
public static void quickSortByLoop(int[] array) {
        // 1. 先创建一个栈, 栈里面存的是待处理区间的下标
        Stack<Integer> stack = new Stack<>();
        // 2. 初始情况下待处理区间, 就是整个数组
        stack.push(array.length - 1);
        stack.push(0);
        while (!stack.isEmpty()) {
            // 3. 取栈顶元素, 栈顶元素就是我们要处理的区间
            int left = stack.pop();
            int right = stack.pop();
            //只有一个元素或者为空
            if (left >= right) {
                continue;
            }
            // 4. 对当前待处理区间进行整理
            int index = partition(array, left, right);
            // 5. 接下来要处理的区间再入栈
            // [left, index - 1]
            // [index + 1, right]
            stack.push(index - 1);
            stack.push(left);

            stack.push(right);
            stack.push(index + 1);
        }
    }

```

#### 归并排序

* 数据允许在外存中
  * 外部排序核心思路就是归并排序
  * 外部排序:内存空间有限，在外存排序
* 归并的排序也是-种给 链表进行排序的算法
* 也是各种标准库中稳定排序算法的主要实现方式

```java
public static void mergeSortByLoop(int[] array) {
        // 借助下标相关的规律来进行分组.
        // 初始情况下, 每个元素单独作为一组
        // [0] [1]    [2] [3]     [4] [5]
        // [0, 1] 和 [2, 3] 合并. [4, 5]  和 [6, 7] 区间合并
        // [0, 1, 2, 3]  [4, 5, 6, 7]
        //gap可以理解为下标的差值
        for (int gap = 1; gap < array.length; gap *= 2) {//一个一个-》两个两个-》。。。。
            for (int i = 0; i < array.length; i += 2 * gap) {
                // 这个循环负责在 gap 为指定值的情况下
                // 把所有的区间进行归并
                // 针对当前的 i, 也能划分出两个需要进行归并的区间
                // [beg, mid)
                // [mid, end)
                int beg = i;
                int mid = i + gap;
                int end = i + 2 * gap;
                //超出末尾
                if (mid > array.length) {
                    mid = array.length;
                }
                if (end > array.length) {
                    end = array.length;
                }
                merge(array, beg, mid, end);
            }
        }
    }

```

### 其他算法

##### 二分查找

> 要求待查找的序列有序。每次取中间位置的值与待查关键字比较，如果中间位置 的值比待查关键字大，则在前半部分循环这个查找的过程，如果中间位置的值比待查关键字小,则在后半部分循环这个查找的过程。直到查找到了为止，否则序列中没有待查的关键字。

```java
public static int binarySearch(int[] arr,int toFind){
        int left = 0;
        int right = arr.length - 1;
        while(left < right){
            int mid = (left + right) /2;
            if(toFind < arr[mid]){
                right = mid - 1;
            }else if(toFind > arr[mid]){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

```

##### 数组逆置

```java
public static void reverse(int[] arr){
        int left = 0;
        int right = arr.length - 1;
        while(left < right){
            int tmp = arr[0];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }
    }


```

## 网络

### 网络在计算机体系中作用？

> 网络之间通过**路由器**互连起来，就构成一个覆盖范围更大的计算机网络，**互连**，由网络构成的互连网（网络的网络）
> 与网络相连的计算机称为**主机**

**路由器：**

* 当IP数据包, 到达路由器时, 路由器会先查看目的IP;
* 路由器决定这个数据包是能直接发送给目标主机, 还是需要发送给下一个路由器;
* 依次反复, 一直到达目标IP地址;
* 路由表可以使用route命令查看；

### TCP/IP体系分层

![img](https://github.com/q1206271031/photo/raw/master/%E7%BD%91%E7%BB%9C%E5%8E%9F%E7%90%86/TCP_IP%E4%BA%94%E5%B1%82%E5%8D%8F%E8%AE%AE.png)

> ***TCP在HTTP之下，保证了可靠性，如何保证可靠性？***

***详见TCP如何保障***

### 数据能传输的三个整体理解

* 局域网内：链路层对链路层（类比货物汽车是可以直接到达另外的城市的）

* 局域网内：应用层对应用层（封装+解包/分用，买东西会经过淘宝，快递，物流）

* 互联网中：应用层对应用层（ip地址不变，mac地址在变化）

  ***mac地址解决同一局域网下问题***

  ***ip是长期目标，mac是短期目标***

**mac地址：**

* MAC地址用来识别数据链路层中相连的节点
* IP地址描述的是路途总体的 起点 和 终点;
* MAC地址描述的是路途上的每一个区间的起点和终点;

##### 应用层协议

* DNS基本工作原理，端口

  > 基于tcp和udp的***53端口***

  * 用户主机上运行着DNS的客户端，就是我们的PC机或者手机客户端运行着DNS客户端了。
  * 浏览器将接收到的url中抽取出域名字段，就是访问的主机名，比如http://www.baidu.com/，并将这个主机名传送给DNS应用的客户端
  * DNS客户机端向DNS服务器端发送一份查询报文，报文中包含着要访问的主机名字段（中间包括一些列缓存查询以及分布式DNS集群的工作）。
  * 该DNS客户机最终会收到一份回答报文，其中包含有该主机名对应的IP地址。
  * 一旦该浏览器收到来自DNS的IP地址，就可以向该IP地址定位的HTTP服务器发起TCP连接。

* HTTP协议(端口80)

  * 工作在传输层的什么协议上？

    > TCP上

  * 应用

    > Tomcat		

  * url(urlencode,urldecode,queryString,path)

  > 将需要转码的字符转为16进制，然后从右到左，取4位(不足4位直接处理)，每2位做一位，前面加上%，编码成%XY格式
  >
  > http://foo.com?x=1&y=2 ,QueryString:x=1&y=2 
  >
  > http://localhost.com:8080/index.htm
  >
  > 协议名：http     服务器地址：localhost.com
  >
  > 端口：8080        对象路径：/index.htm

  * 请求/响应（request,response）

    > schema://username:password@host:port/path?query_ string#segment

    * 基本格式

      > *//请求*
      >
      > POST **/**index**.**html HTTP**/**1.1
      > HOST: www**.**XXX**.**com
      > **User**-**Agent: **Mozilla/**5.0**(Windows** NT 6.1**;**rv:15.0**)** **Firefox**/**15.0**
      >
      > Username=**admin**&**password**=admin
      >
      > *//响应*
      > HTTP**/**1.1 200 OK
      > **Content**-**Encoding: gzip**
      >
      > **Content-**Type: text**/**html**;**charset**=**utf**-**8

    * 请求方法

      > GET获取资源	POST传输实体主类

    * 响应状态（项目）	

    ![1582117890435](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1582117890435.png)

    * 头信息

    > *//请求*
    >
    > POST **/**index**.**html HTTP**/**1.1
    >
    > *//响应*
    > HTTP**/**1.1 200 OK

  * Cookie+Session

    > Cookie
    >
    > 在网站中，http请求是无状态的。也就是说即使第一次和服务器连接后并且登录成功后，第二次请求服务器依然不能
    > 知道当前请求是哪个用户。cookie的出现就是为了解决这个问题，第一次登录后服务器返回一些数据（cookie）给浏
    > 览器，然后浏览器保存在本地，当该用户发送第二次请求的时候，就会自动的把上次请求存储的cookie数据自动的携带
    > 给服务器，服务器通过浏览器携带的数据就能判断当前用户是哪个了。cookie存储的数据量有限，不同的浏览器有不同
    > 的存储大小，但一般不超过4KB。因此使用cookie只能存储一些小量的数据。
    >
    > Session
    >
    > session和cookie的作用有点类似，都是为了存储用户相关的信息。不同的是，cookie是存储在本地浏览器，而session存储在服务器。存储在服务器的数据会更加的安全，不容易被窃取。但存储在服务器也有一定的弊端，就是会占用服务器的资源，但现在服务器已经发展至今，一些session信息还是绰绰有余的
    >
    > ![1582118308044](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1582118308044.png) 
    >
    > ![1582118328199](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1582118328199.png)

  * https（端口443）

    > HTTP 下加入 SSL 层
    >
    > 建立连接获取证书，证书验证，数据加密和传输

##### 传输层协议

* 作用：端（进程）对端的传输

* ip的作用，port的作用，五元组的概念

  > * 网络层:IP源ip+目的IP IP标识的是网络上的唯一的一台结点(主机)
  > * 传输层: Port (端口) 源port +目的port port标识的是主机上的唯—个进程 进程(人) 端口(电话号码) 人可以有多个电话号码，但是一个电话号码只能属于一个人(备注)
  > * ip+port标识网络，上的唯一一台主机上的唯一一个进程
  > * 网络传输分双方
  > * 操作系统提供给用户态的网络接口是socket (套接字)
  > * 四元组：本地ip+本地port+目标ip+目标port
  > * 五元组：加一个协议TCP/UDP

* UDP知识点（和TCP对比看）

  > 无连接: 知道对端的IP和端口号就直接进行传输, 不需要建立连接;
  > 不可靠: 没有确认机制, 没有重传机制; 如果因为网络故障该段无		法发到对方, UDP协议层也不会给应用层返回任何错误信息;
  > 面向数据报: 不能够灵活的控制读写数据的次数和数量;

  * UDP保证可靠性（放在应用层去做）

* TCP协议

#### TCP可靠性,面向连接，面向流

##### 可靠性是指：

* 数据不会出错
* 对方是否收到已知
* 数据是有序的
* 数据不会重复
* 可以做到流量控制
* 数据包大小可控

##### 如何保障？

* 校验和
* 确认应答+超时重传+序列号
* 序列号
* 序列号
* 滑动窗口+流量控制+拥塞控制
* MSS(Max segment size)最大发货量

> 3.连接管理(面向连接)

##### 什么是连接管理

* 为什么要连接管理
* 连接的生命周期
* 为什么需要建立连接
* 三次握手，四词挥手（代码对应）
* 状态转移

​	TIME_WAIT	CLOSE_WAIT

##### 字节流

* 发送和接收缓冲区
  * 为什么要有发送缓冲区（流量控制+超时重传）
  * 为什么要有接收缓冲区
    * 应用层不一定马上读取
    * 保证输入端数据有序
* 应用层数据发送成功含义
  * 只是发送到了发送缓冲区

##### TCP异常情况

* RST
* 应用层如何感知

##### 其他零星

* 延迟应答2.捎带应答3.快重传问题
* 黏包（面向流）
  * HTTP协议格式（为什么需要Content_length和空行）

##### 网络层（地图）

* 作用，寻路
* IP协议
  * IP地址划分
    * 五类地址
    * 网络掩码
    * 公网vs内网
    * 本地IP	
  * 路由（寻路）机制
  * 分片（货物太多/路太窄过不去，一辆过不去车队过）
    * MTU 2.MSS(可靠性)  3.UDP(不可靠性)
* ARP协议
  * 作用：IP和mac地址的转换

##### 零星知识：

* NAT协议
* 代理，路由，转发

##### 工具，故障分析过程	

|     目的     | windows           | linux                       |
| :----------: | ----------------- | --------------------------- |
|  网络通不通  | ping              | ping                        |
|     dns      | ping/nslookup     | telnet/netcat               |
|  端口通不通  | telnet            | telnnet/netcat              |
|     抓包     | wireshark/fiddler | tcpdump                     |
| 查看网络状态 | netstat           | netstat                     |
|    防火墙    | 防火墙            | 云服务器/iptables/firewalld |

