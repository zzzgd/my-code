//假设有一个同时存储文件和目录的文件系统。下图展示了文件系统的一个示例： 
//
// 
//
// 这里将 dir 作为根目录中的唯一目录。dir 包含两个子目录 subdir1 和 subdir2 。subdir1 包含文件 file1.ext 和子目
//录 subsubdir1；subdir2 包含子目录 subsubdir2，该子目录下包含文件 file2.ext 。 
//
// 在文本格式中，如下所示(⟶表示制表符)： 
//
// 
//dir
//⟶ subdir1
//⟶ ⟶ file1.ext
//⟶ ⟶ subsubdir1
//⟶ subdir2
//⟶ ⟶ subsubdir2
//⟶ ⟶ ⟶ file2.ext
// 
//
// 如果是代码表示，上面的文件系统可以写为 "dir
//\tsubdir1
//\t\tfile1.ext
//\t\tsubsubdir1
//\tsubdir2
//\t\tsubsubdir2
//\t\t\tfile2.ext" 。'
//' 和 '\t' 分别是换行符和制表符。 
//
// 文件系统中的每个文件和文件夹都有一个唯一的 绝对路径 ，即必须打开才能到达文件/目录所在位置的目录顺序，所有路径用 '/' 连接。上面例子中，指向 
//file2.ext 的 绝对路径 是 "dir/subdir2/subsubdir2/file2.ext" 。每个目录名由字母、数字和/或空格组成，每个文件名遵循 
//name.extension 的格式，其中 name 和 extension由字母、数字和/或空格组成。 
//
// 给定一个以上述格式表示文件系统的字符串 input ，返回文件系统中 指向 文件 的 最长绝对路径 的长度 。 如果系统中没有文件，返回 0。 
//
// 
//
// 示例 1： 
//
// 
//输入：input = "dir
//\tsubdir1
//\tsubdir2
//\t\tfile.ext"
//输出：20
//解释：只有一个文件，绝对路径为 "dir/subdir2/file.ext" ，路径长度 20
// 
//
// 示例 2： 
//
//  /dir,  /dir/subdir1,
//
//
// 
//输入：input = "dir
//\tsubdir1
//\t\tfile1.ext
//\t\tsubsubdir1
//\tsubdir2
//\t\tsubsubdir2
//\t\t\tfile2.ext"
//输出：32
//解释：存在两个文件：
//"dir/subdir1/file1.ext" ，路径长度 21
//"dir/subdir2/subsubdir2/file2.ext" ，路径长度 32
//返回 32 ，因为这是最长的路径 
//
// 示例 3： 
//
// 
//输入：input = "a"
//输出：0
//解释：不存在任何文件 
//
// 示例 4： 
//
// 
//输入：input = "file1.txt
//file2.txt
//longfile.txt"
//输出：12
//解释：根目录下有 3 个文件。
//因为根目录中任何东西的绝对路径只是名称本身，所以答案是 "longfile.txt" ，路径长度为 12
// 
//
// 
//
// 提示： 
//
// 
// 1 <= input.length <= 10⁴ 
// input 可能包含小写或大写的英文字母，一个换行符 '
//'，一个制表符 '\t'，一个点 '.'，一个空格 ' '，和数字。 
// 
// Related Topics 栈 深度优先搜索 字符串 👍 238 👎 0


  
package com.zgd.leetcode.editor.cn;




import org.junit.Test;

import java.util.Stack;
import java.util.regex.Pattern;

public class LongestAbsoluteFilePath{

  /**
  * 388
  * 文件的最长绝对路径
  *
   * 这个题其实坑的地方在于, 对\n,\t的处理,分割. 正则匹配关系的了解
  * 
  *
  * 2022-07-29 15:10:06
  */  
  public static void main(String[] args) {
    Solution solution = new LongestAbsoluteFilePath().new Solution();
    //dir/subdir2/file.ext
    solution.lengthLongestPath("dir\\n\\tsubdir1\\n\\tsubdir2\\n\\t\\tfile.ext");
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthLongestPath(String input) {
        //利用栈的结构实现. 比如有如下文件夹. 首先解析几个tab符号(\t), 如果比上一个多1, 则取出上一个字符串,拼接.
        //如果发现tab比之前的少,就弹栈,弹出一个tab符-1,表示往上退一个文件夹. 直到tab又比上一个多1,就拼接
        // a
        // ->ab
        // ->->abc
        // ->->->abcd
        // ->ac


        //1. 先按\n分割成多行 这里用\n分割,提交到leetcode会有问题,好像识别转义不知是多转义了还是少转义了,和本地不一样
        String[] files = input.split("\\n");
//        String[] files = input.split(new String("\\\\")+"n");
        //默认层级
        int level = 0;
        int maxl = 0;
        Stack stack = new Stack();
        for (String file : files) {
            //先判断有几个tab符
            int tabNum = 0;
            while(file.startsWith("\t")){
                file = file.substring(1);
                tabNum++;
            }

            while(tabNum <= level && !stack.isEmpty()){
                //如果tab比上个级别小,需要往上返回文件夹,也就是弹栈,
                stack.pop();
                level--;
            }
            //再取出来拼接
            if (!stack.isEmpty()){
                //从栈peek出来拼接,但是不弹栈
                file = stack.peek() +"/" + file;
            }
            //再压栈
            stack.push(file);
            //级别为当前tab符数量
            level = tabNum;
            //如果是文件,比较长度
            if (file.contains(".")){
                maxl = Math.max(maxl,file.length());
            }
//            System.out.println(file);
        }
        return maxl;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    @Test
    public void fun01(){
      String str = "a\nbb\\nc0cc\\\ndddd\\\\neeee";

        System.out.println("str = " + str);
        /*
        a
        bb\ccc\
        dddd\\neeee
         */
//        String[] ss = str.split("\n");  //["a", "bb\nccc\", "dddd\\neeee"]  不进
//        String[] ss1 = str.split("\\n");  //["a", "bb\nccc\", "dddd\\neeee"]  进p
//        String[] ss2 = str.split("\\\n");  //["a", "bb\nccc\", "dddd\\neeee"]  不进
//        String[] ss3 = str.split("\\\\n");  //["a\nbb", "ccc\\ndddd\", "eeee"] 进
        String[] ss = Pattern.compile("\n").split(str);  //["a", "bb\ccc\", "dddd\\neeee"]  不进
        String[] ss1 = Pattern.compile("\\n").split(str);//["a", "bb\ccc\", "dddd\\neeee"]  进p
        String[] ss2 = Pattern.compile("\\\n").split(str); //["a", "bb\ccc\", "dddd\\neeee"]  不进
        String[] ss3 = Pattern.compile("\\\\n").split(str);  //["a\nbb", "ccc\\ndddd\", "eeee"] 进

        String str1 = "\\nada";
        System.out.println(str1.startsWith("\n")); //F
        System.out.println(str1.startsWith("\\n")); //T
        System.out.println(str1.startsWith("\\\n")); //F
        System.out.println(str1.startsWith("\\\\n")); //F

    }

}