package com.stmik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    public void setUp() {
        account = new BankAccount(100.0);   // saldo awal 100
    }

    @Test
    public void testSaldoAwalDefaultNol() {
        BankAccount baru = new BankAccount();
        assertEquals(0.0, baru.getBalance(), 0.001);
    }

    @Test
    public void testSaldoAwalNegatifMelemparException() {
        assertThrows(IllegalArgumentException.class,
                () -> new BankAccount(-1));
    }

    @Test
    public void testDepositMenambahSaldo() {
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), 0.001);
    }

    @Test
    public void testDepositNolTidakMengubahSaldo() {
        account.deposit(0);
        assertEquals(100.0, account.getBalance(), 0.001);
    }

    @Test
    public void testDepositNegatifMelemparException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> account.deposit(-10.0));
        assertEquals("Jumlah deposit tidak boleh negatif", ex.getMessage());
    }

    @Test
    public void testWithdrawMengurangiSaldo() {
        account.withdraw(40.0);
        assertEquals(60.0, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawSeluruhSaldo() {
        account.withdraw(100.0);          // tepat sama dengan saldo
        assertEquals(0.0, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawNegatifMelemparException() {
        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(-5.0));
    }

    @Test
    public void testWithdrawMelebihiSaldoMelemparException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(500.0));
        assertEquals("Saldo tidak mencukupi", ex.getMessage());
        assertEquals(100.0, account.getBalance(), 0.001); // saldo tidak berubah
    }
}
