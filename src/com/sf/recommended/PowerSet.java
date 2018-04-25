package com.sf.recommended;

/**
 * 在推荐系统中，根据各个用户对不同推荐结果的兴趣度（一般通过用户是否点击查看甚至是学习该课程来确定），
 * 来对各种推荐算法对推荐结果的影响来做调整，对于在用户感兴趣这一推荐结果的产生做出更大贡献的算法
 * 则调高其权值，否则就调低权值
 *
 * 思路：
 * 前端将用户浏览记录保存在数据库中
 * 将浏览记录与之前的推荐数据进行匹配
 * 如果某个算法推荐出来了这歌课程
 * 则根据其之前的权值增加计算出一个新的权值
 * Created by sf on 2017/9/7.
 */
public class PowerSet {
}
