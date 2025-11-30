package com.mashibing.strategy.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * DFA有限状态自动机
 */
public class DFAUtil {
    // 敏感词树
    private static Map dfaMap = new HashMap<>();
    private static final String IS_END = "isEnd";

    public static void main(String[] args) {
        // 敏感词库
        Set<String> dirtyWords = new HashSet<>();
        dirtyWords.add("三胖");
        dirtyWords.add("山炮");
        dirtyWords.add("三胖啊啊");
        dirtyWords.add("三胖啊啊");
        // 测试敏感词树的生成
        create(dirtyWords);
        // 输出结果
        for (Object o : dfaMap.entrySet()) {
            System.out.println(o);
        }
    }

    public static void create(Set<String> dirtyWords) {
        //1、 声明一个Map作为临时存储
        Map nowMap;
        //2、遍历敏感词库
        for (String dirtyWord : dirtyWords) {
            nowMap = dfaMap;
            // 每个词，依次获取
            for (int i = 0; i < dirtyWord.length(); i++) {
                // 获取敏感词的每个字
                String word = String.valueOf(dirtyWord.charAt(i));
                // 判断当前的敏感词树中是否包含当前的字
                Map map = (Map) nowMap.get(word);
                if (map == null) {
                    // 当前敏感词树中没有这个字
                    map = new HashMap();
                    // 将当前的敏感词存入
                    nowMap.put(word, map);
                }
                // 操作当前key对应的value的map
                nowMap = map;
                // 如果当前的字，已经有IS_END了，并且值为1，直接不管，
                if (nowMap.containsKey(IS_END) && nowMap.get(IS_END).equals("1")) {
                    continue;
                }
                // 如果当前的isEnd没有，或者是0，需要考虑需要改为1
                if (i == dirtyWord.length() - 1) {
                    // 到最后一个字了。
                    nowMap.put(IS_END, "1");
                } else {
                    // isEnd之前就是0，或者压根就没有isEnd
                    nowMap.putIfAbsent(IS_END, "0");
                }
            }
        }

    }

}
