package com.plusl.ai.codewith.ui;

import com.plusl.ai.codewith.session.Session;
import com.plusl.ai.codewith.session.SessionManager;
import org.springframework.boot.CommandLineRunner;

import java.util.Scanner;

/**
 * 命令行运行器
 *
 * @Author PlusL
 */
//@Component
public class CliRunner implements CommandLineRunner {
    
    private final SessionManager sessionManager;
    
    public CliRunner(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
    
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Session session = sessionManager.createSession();
        
        System.out.println("AI Code Companion CLI");
        System.out.println("输入 'exit' 退出程序");
        System.out.println("------------------------");
        
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            
            if ("exit".equalsIgnoreCase(input.trim())) {
                System.out.println("再见！");
                break;
            }
            
            if (!input.trim().isEmpty()) {
                String response = session.onUserMessage(input);
                System.out.println(response);
            }
        }
        
        scanner.close();
    }
}