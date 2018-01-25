package com.rayhahah.kotlinstudy.learn.highFunction


/**
 * 尾递归优化的学习
 */

data class ListNode(val value: Int, var next: ListNode? = null)

/**
 * 这个就属于尾递归
 */
tailrec fun findListNode(head: ListNode?, value: Int): ListNode? {
    head ?: return null
    if (head.value == value) return head
    return findListNode(head.next, value)
}

/**
 * 因为存在n*,所以就不是尾递归了
 */
fun factorial(n: Long): Long {
    return n * factorial(n - 1)
}

