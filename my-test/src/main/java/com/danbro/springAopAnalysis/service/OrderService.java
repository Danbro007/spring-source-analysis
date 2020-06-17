package com.danbro.springAopAnalysis.service;


import com.danbro.springAopAnalysis.entities.Order;

/**
 * @Classname OrderService
 * @Description TODO
 * @Date 2020/6/8 11:05
 * @Author Danrbo
 */
public interface OrderService {

	Order createOrder(String username, String product);

	Order queryOrder(String username);
}