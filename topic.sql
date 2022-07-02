/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 02/07/2022 16:16:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content_text` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `stars` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1543066000974872578 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (1, '# 两数之和\n\n题目描述：给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。\n***\n自己的方案：\n\n```java\nclass Solution {\n    public int[] twoSum(int[] nums, int target) {\n        int n = nums.length;\n        for (int i = 0; i < n; ++i) {\n            for (int j = i + 1; j < n; ++j) {\n                if (nums[i] + nums[j] == target) {\n                    return new int[]{i, j};\n                }\n            }\n        }\n        return new int[0];\n    }\n}\n```\n\n两层循环，暴力匹配。\n***\n官方优秀方案：\n\n```java\nclass Solution {\n    public int[] twoSum(int[] nums, int target) {\n        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();\n        for (int i = 0; i < nums.length; ++i) {\n            if (hashtable.containsKey(target - nums[i])) {\n                return new int[]{hashtable.get(target - nums[i]), i};\n            }\n            hashtable.put(nums[i], i);\n        }\n        return new int[0];\n    }\n}\n\n作者：LeetCode-Solution\n链接：https://leetcode-cn.com/problems/two-sum/solution/liang-shu-zhi-he-by-leetcode-solution/\n来源：力扣（LeetCode）\n著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。\n```\n\n利用HashMap（哈希表加map），HashMap中存放的是每个位置的值和对应位置坐标，原暴力解法是存放在数组中，查找值需要遍历数组，现在存放在HashMap中查找值时不需要遍历，但多出来了HashMap的空间，等于用空间换时间。当指针走到n位置时：\n\n- 判断与nums[n]对应的target-nums[n]是否存在，不存在把nums[n]值对应着位置n一起存入哈希表中，为后边元素匹配做准备。\n- 如果与nums[n]对应的target-nums[n]存在，获得两个位置的坐标输出。（nums[n]对应坐标就是n，target-nums[n]是值，在HashMap中是键，所以get方法可以取出对应值（就是坐标值））\n', '两数之和', '哈希表', '张三', NULL);
INSERT INTO `blog` VALUES (2, '# 有效的括号1\n\n题目描述：给定一个只包括 \'(\'，\')\'，\'{\'，\'}\'，\'[\'，\']\' 的字符串 s ，判断字符串是否有效。\n\n有效字符串需满足：\n\n- 左括号必须用相同类型的右括号闭合。\n- 左括号必须以正确的顺序闭合。\n\n来源：力扣（LeetCode）\n链接：https://leetcode-cn.com/problems/valid-parentheses\n著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。\n\n***\n\n官方方案：\n\n```java\nclass Solution {\n    public boolean isValid(String s) {\n        int n = s.length();\n        if (n % 2 == 1) {\n            return false;\n        }\n\n        Map<Character, Character> pairs = new HashMap<Character, Character>() {{\n            put(\')\', \'(\');\n            put(\']\', \'[\');\n            put(\'}\', \'{\');\n        }};\n        Deque<Character> stack = new LinkedList<Character>();\n        for (int i = 0; i < n; i++) {\n            char ch = s.charAt(i);\n            if (pairs.containsKey(ch)) {\n                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {\n                    return false;\n                }\n                stack.pop();\n            } else {\n                stack.push(ch);\n            }\n        }\n        return stack.isEmpty();\n    }\n}\n\n作者：LeetCode-Solution\n链接：https://leetcode-cn.com/problems/valid-parentheses/solution/you-xiao-de-gua-hao-by-leetcode-solution/\n来源：力扣（LeetCode）\n著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。\n```\n\n括号都是成对出现的，如果字符串长度为奇数，一定是无效的，从头遍历，如果是左括号就放进栈中。如果是有效字符串，遍历完左括号后，某一左括号出现的次序越靠后，则接下来对应的右括号出现的次序越靠前。，所以我们这里用栈这种结构。遇到右括号，取出栈顶的符号，如果是有效字符串，则栈顶的符号一定是与右括号对应的左括号，遍历完右括号，栈中对应的左括号应该被完全取出，栈是空的。\n\n注意：\n\n- 整个过程中，右括号没有入栈。\n- 遍历右括号过程中，若栈提前为空，说明没有与右括号对应的左括号，若右括号与栈顶左括号不对应，则说明括号顺序不对。\n- 遍历完后，若栈不为空，则说明没有与左括号对应的右括号。', '有效的括号1', '哈希表', '张三', NULL);
INSERT INTO `blog` VALUES (3, '# 合并两个有序链表\n\n题目描述：将两个升序链表合并为一个新的 **升序** 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。\n\n```java\n/**\n * Definition for singly-linked list.\n * public class ListNode {\n *     int val;\n *     ListNode next;\n *     ListNode() {}\n *     ListNode(int val) { this.val = val; }\n *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }\n * }\n */\nclass Solution {\n    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {\n        ListNode head=new ListNode();\n        ListNode temp=head;\n        while(l1!=null||l2!=null){\n            if(l2==null){\n                temp.next=l1;\n                break;\n            }else if(l1==null){\n                temp.next=l2;\n                break;\n            }else if(l1.val<=l2.val){\n                temp.next=l1;\n                l1=l1.next;\n            }else if(l1.val>l2.val){\n                temp.next=l2;\n                l2=l2.next;\n            }\n            temp=temp.next;\n        }\n        return head.next;\n    }\n}\n```\n\n**这道题和88题合并两个有序数组思路一模一样（双指针法）**，首先，我们设定一个哨兵节点 prehead ，这可以在最后让我们比较容易地返回合并后的链表。我们维护一个 prev 指针，我们需要做的是调整它的 next 指针。然后，我们重复以下过程，直到 l1 或者 l2 指向了 null ：如果 l1 当前节点的值小于等于 l2 ，我们就把 l1 当前的节点接在 prev 节点的后面同时将 l1 指针往后移一位。否则，我们对 l2 做同样的操作。不管我们将哪一个元素接在了后面，我们都需要把 prev 向后移一位。\n\n在循环终止的时候， l1 和 l2 至多有一个是非空的。由于输入的两个链表都是有序的，所以不管哪个链表是非空的，它包含的所有元素都比前面已经合并链表中的所有元素都要大。这意味着我们只需要简单地将非空链表接在合并链表的后面，为了使返回的链表不带头节点，我们返回头节点的下一节点。\n', '合并两个有序链表', '双指针', '李四', NULL);
INSERT INTO `blog` VALUES (4, '# 矩阵置零\n\n题目描述：给定一个 `*m* x *n*` 的矩阵，如果一个元素为 **0** ，则将其所在行和列的所有元素都设为 **0** 。\n\n***\n\n自己的方案：\n\n```java\nclass Solution {\n    public void setZeroes(int[][] matrix) {\n        int m=matrix.length;\n        int n=matrix[0].length;\n        List<Integer> list=new ArrayList<>(10);\n        for(int i=0;i<m*n;i++){\n            if(matrix[i/n][i%n]==0){\n                list.add(i);\n            }\n        }\n        for(int num:list){\n            for(int i=0;i<m;i++){\n                matrix[i][num%n]=0;\n            }\n            for(int i=0;i<n;i++){\n                matrix[num/n][i]=0;\n            }\n        }\n    }\n}\n```\n\n遍历数组，用list记录值为零的位置，这里记录的是0到mn之间的整数，原因是使用了一种表示二维数组元素位置的替换法：任意x（0到mn）对应二维数组中第x/n行，第x%n列；这种方法在566题重塑矩阵那里详细介绍了。\n\n然后取出记录，把每个为0的位置所在行和所在列置为0。\n\n***\n\n官方优化方案：\n\n```java\nclass Solution {\n    public void setZeroes(int[][] matrix) {\n        int m = matrix.length, n = matrix[0].length;\n        boolean flagCol0 = false, flagRow0 = false;\n        for (int i = 0; i < m; i++) {\n            if (matrix[i][0] == 0) {\n                flagCol0 = true;\n            }\n        }\n        for (int j = 0; j < n; j++) {\n            if (matrix[0][j] == 0) {\n                flagRow0 = true;\n            }\n        }\n        for (int i = 1; i < m; i++) {\n            for (int j = 1; j < n; j++) {\n                if (matrix[i][j] == 0) {\n                    matrix[i][0] = matrix[0][j] = 0;\n                }\n            }\n        }\n        for (int i = 1; i < m; i++) {\n            for (int j = 1; j < n; j++) {\n                if (matrix[i][0] == 0 || matrix[0][j] == 0) {\n                    matrix[i][j] = 0;\n                }\n            }\n        }\n        if (flagCol0) {\n            for (int i = 0; i < m; i++) {\n                matrix[i][0] = 0;\n            }\n        }\n        if (flagRow0) {\n            for (int j = 0; j < n; j++) {\n                matrix[0][j] = 0;\n            }\n        }\n    }\n}\n\n作者：LeetCode-Solution\n链接：https://leetcode-cn.com/problems/set-matrix-zeroes/solution/ju-zhen-zhi-ling-by-leetcode-solution-9ll7/\n来源：力扣（LeetCode）\n著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。\n```\n\n原方法用列表记录位置，需要开辟额外的空间，优化方法用第一行和第一列记录为0的位置，无额外空间开销，但这样会改变第一行和第一列的数据，所以要用两个变量来标记第一行是否含有0，第一列是否含有0。', '矩阵置零', '枚举', '李四', NULL);
INSERT INTO `blog` VALUES (5, '# 有效的字母异位词\n\n题目描述：给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。\n\n注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。\n\n来源：力扣（LeetCode）\n链接：https://leetcode-cn.com/problems/valid-anagram\n著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。\n\n***\n\n自己的方案：\n\n```java\nclass Solution {\n    public boolean isAnagram(String s, String t) {\n        if(s.length()!=t.length()) return false;\n        char[] char1=s.toCharArray();\n        char[] char2=t.toCharArray();\n\n        Arrays.sort(char1);\n        Arrays.sort(char2);\n\n        for(int i=0;i<s.length();i++){\n            if(char1[i]!=char2[i]) return false;\n        }\n        return true;\n    }\n}\n```\n\n这道题与找两个数组中相同元素有关，与350题两个数组的交集和383题赎金信两题非常相似，所以这题首先可以想到两种方法，分别遍历计数，或者排序对比相同位置的元素是否一致，上面的方法采用了排序的方法。\n\n然后是计数方法，有两种计数方式，用**数组计数或HashMap计数**\n\n数组计数：\n\n```java\nclass Solution {\n    public boolean isAnagram(String s, String t) {\n        if (s.length() != t.length()) {\n            return false;\n        }\n        int[] table = new int[26];\n        for (int i = 0; i < s.length(); i++) {\n            table[s.charAt(i) - \'a\']++;\n        }\n        for (int i = 0; i < t.length(); i++) {\n            table[t.charAt(i) - \'a\']--;\n            if (table[t.charAt(i) - \'a\'] < 0) {\n                return false;\n            }\n        }\n        return true;\n    }\n}\n\n作者：LeetCode-Solution\n链接：https://leetcode-cn.com/problems/valid-anagram/solution/you-xiao-de-zi-mu-yi-wei-ci-by-leetcode-solution/\n来源：力扣（LeetCode）\n著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。\n```\n\n数组计数相比HashMap计数省去了频繁的存取，节省了大量时间。但是如果字符串包含 unicode 字符用数组则行不通，因为你无法知道某个字符的unicode编码有多大，所以创建数组时无法确认数组长度。这时就需要用HashMap计数了\n\n```java\nclass Solution {\n    public boolean isAnagram(String s, String t) {\n        if (s.length() != t.length()) {\n            return false;\n        }\n        Map<Character, Integer> table = new HashMap<Character, Integer>();\n        for (int i = 0; i < s.length(); i++) {\n            char ch = s.charAt(i);\n            table.put(ch, table.getOrDefault(ch, 0) + 1);\n        }\n        for (int i = 0; i < t.length(); i++) {\n            char ch = t.charAt(i);\n            table.put(ch, table.getOrDefault(ch, 0) - 1);\n            if (table.get(ch) < 0) {\n                return false;\n            }\n        }\n        return true;\n    }\n}\n\n作者：LeetCode-Solution\n链接：https://leetcode-cn.com/problems/valid-anagram/solution/you-xiao-de-zi-mu-yi-wei-ci-by-leetcode-solution/\n来源：力扣（LeetCode）\n著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。\n```\n\n', '有效的字母异位词', '计数', '李四', NULL);
INSERT INTO `blog` VALUES (6, '# 两个数组的交集2\n\n题目描述：给定两个数组，编写一个函数来计算它们的交集。\n***\n自己的方案（参考官方哈希表思路）：\n\n```java\nclass Solution {\n    public int[] intersect(int[] nums1, int[] nums2) {\n        Map<Integer,Integer> map=new HashMap<>();\n        for(int i:nums1){\n            map.put(i,map.getOrDefault(i,0)+1);\n        }\n        int[] array=new int[nums1.length];\n        int index=0;\n        for(int j:nums2){\n            int count=map.getOrDefault(j,0);\n            if(count>0){\n                array[index++]=j;\n                count--;\n                if(count>0){\n                    map.put(j,count);\n                }else{\n                    map.remove(j);\n                }\n            }\n        }\n        return Arrays.copyOfRange(array,0,index);\n    }\n}\n```\n\n**注意这不是最大字串问题，不需要连续，不需要保持顺序**，只需找出相同的元素。先遍历其中数组1，把每个元素值映射着出现次数存储在HashMap中，然后遍历数组2，看数组2中的元素在HashMap中是否存在，如果存在则取出并且HashMap中次数减一。\n***\n另一方案为排序加双指针：\n\n```java\nclass Solution {\n    public int[] intersect(int[] nums1, int[] nums2) {\n        int[] array=new int[nums1.length];\n        Arrays.sort(nums1);\n        Arrays.sort(nums2);\n        int index=0;\n        int i=0;\n        int j=0;\n        while(i<nums1.length&&j<nums2.length){\n            if(nums1[i]<nums2[j]){\n                i++;\n            }else if(nums1[i]>nums2[j]){\n                j++;\n            }else{\n                array[index++]=nums1[i];\n                i++;\n                j++;\n            }\n        }\n        return Arrays.copyOfRange(array,0,index);\n    }\n}\n```\n\n先对两数组进行排序，然后两指针先指向两数组开头，每次比较指针位置的两值大小，值小的指针向右移动，若相等则赋值给结果数组。\n', '两个数组的交集2', '双指针', '张三', NULL);

-- ----------------------------
-- Table structure for blog_star
-- ----------------------------
DROP TABLE IF EXISTS `blog_star`;
CREATE TABLE `blog_star`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `blog_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1543072129901584387 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_star
-- ----------------------------
INSERT INTO `blog_star` VALUES (1, '矩阵置零', '张三');
INSERT INTO `blog_star` VALUES (2, '合并两个有序链表', '张三');
INSERT INTO `blog_star` VALUES (3, '有效的字母异位词', '张三');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `father_id` bigint(20) NULL DEFAULT NULL,
  `create_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `target_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1543065837464125442 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '[可爱][哈哈][嘻嘻][微笑]', '张三', NULL, '2022-04-13 11:57:15', '矩阵置零', 1);
INSERT INTO `comment` VALUES (1543046411637370881, '111', '张三', 1, NULL, '两数之和', 2);
INSERT INTO `comment` VALUES (1543047247956422658, '1111', '张三', NULL, NULL, '两数之和', NULL);
INSERT INTO `comment` VALUES (1543055015438446594, '[可爱]', '张三', NULL, '2022-07-02 10:12:38', '合并两个有序链表', NULL);
INSERT INTO `comment` VALUES (1543065594643283970, '[害羞]', '张三', NULL, '2022-07-02 10:54:41', '两数之和', NULL);
INSERT INTO `comment` VALUES (1543065625861488642, '[汗]', '张三', NULL, '2022-07-02 10:54:48', '两数之和', NULL);
INSERT INTO `comment` VALUES (1543065658535116802, '[太开心]', '张三', NULL, '2022-07-02 10:54:56', '两数之和', NULL);
INSERT INTO `comment` VALUES (1543065782023815169, '123', '张三', NULL, '2022-07-02 10:55:25', '两数之和', NULL);
INSERT INTO `comment` VALUES (1543065807198027777, '1231231', '张三', NULL, '2022-07-02 10:55:31', '两数之和', NULL);
INSERT INTO `comment` VALUES (1543065837464125441, '1231231', '张三', NULL, '2022-07-02 10:55:39', '两数之和', NULL);

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES (1, '哈希表');
INSERT INTO `label` VALUES (2, '栈');
INSERT INTO `label` VALUES (3, '递归');
INSERT INTO `label` VALUES (4, '动态规划');
INSERT INTO `label` VALUES (5, '枚举');
INSERT INTO `label` VALUES (6, '双指针');
INSERT INTO `label` VALUES (7, '滑动窗口');
INSERT INTO `label` VALUES (8, '优先队列');
INSERT INTO `label` VALUES (9, '深度优先搜索');
INSERT INTO `label` VALUES (10, '广度优先搜索');
INSERT INTO `label` VALUES (11, '分治');
INSERT INTO `label` VALUES (12, '回溯');
INSERT INTO `label` VALUES (13, '贪心算法');
INSERT INTO `label` VALUES (14, '二分查找');
INSERT INTO `label` VALUES (15, '排序');
INSERT INTO `label` VALUES (16, '二叉树');
INSERT INTO `label` VALUES (17, '优先队列');
INSERT INTO `label` VALUES (18, '计数');
INSERT INTO `label` VALUES (19, '二叉搜索数');
INSERT INTO `label` VALUES (20, '单调栈');
INSERT INTO `label` VALUES (21, '单调栈');
INSERT INTO `label` VALUES (22, '字符串匹配');
INSERT INTO `label` VALUES (23, '图');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1543036472722337795 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', 'c4ca4238a0b923820dcc509a6f75849b', '');
INSERT INTO `user` VALUES (2, '李四', 'e10adc3949ba59abbe56e057f20f883e', '');
INSERT INTO `user` VALUES (1543036472722337794, '111', 'c4ca4238a0b923820dcc509a6f75849b', NULL);

SET FOREIGN_KEY_CHECKS = 1;
