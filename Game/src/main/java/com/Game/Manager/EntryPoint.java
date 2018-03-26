package com.Game.Manager;

/**
 * Стартовая тока приложения
 *  @author titaninus
 *  @version 1.0
 */
public class EntryPoint {
    /**
     * Функция запуска приложения
     * @param args - параметры коммандной строки
     */
    public static void  main(String[] args) {
        GameManager.getInstance().Awake();
    }
}
