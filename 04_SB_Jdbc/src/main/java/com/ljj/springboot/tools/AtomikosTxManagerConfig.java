package com.ljj.springboot.tools;




import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;


import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * TransactionManager配置类
 * 写法固定，不用看
 */
@Configuration
public class AtomikosTxManagerConfig {

    @Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
    public TransactionManager atomikosTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }

    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws SystemException {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(1800000);
        return userTransactionImp;
    }

    @Bean(name = "txManager")
    public PlatformTransactionManager txManager() throws SystemException {
        UserTransaction userTransaction = userTransaction();
        TransactionManager transactionManager = atomikosTransactionManager();
        return new JtaTransactionManager(userTransaction, transactionManager);
    }
}