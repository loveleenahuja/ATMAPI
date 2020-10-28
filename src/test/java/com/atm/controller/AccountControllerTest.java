package com.atm.controller;

import com.atm.repository.AccountRepository;
import com.atm.service.BankService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.atm.model.Account;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyObject;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountRepository accountRepo;

    @MockBean
    private BankService bankService;

//    @Before
//    public void setUp() {
//        Account mockaccount = new Account("john", "abc", 20, "john", "doe");
//
//        Mockito.when(
//                accountRepo.findAccountByUsername(Mockito.anyString())).thenReturn(mockaccount);
//    }

    @Test
    void createAccount() throws Exception{
        Account mockaccount = new Account("john", "abc", 20, "john", "doe");

        String exampleAccountJson = "{\"username\":\"john\",\"password\":\"abc\",\"firstname\":\"john\",\"lastname\":\"doe\"}";

        Mockito.when(accountRepo.save(Mockito.any(Account.class))).thenReturn(mockaccount);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/account/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(exampleAccountJson);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{username:john,password:abc,balance:20,firstname:john,lastname:doe}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    void deposit() throws Exception {
        Account mockaccount = new Account("john", "abc", 20, "john", "doe");

        Account mockaccount2 = new Account("john", "abc", 40, "john", "doe");

        Mockito.when(
                accountRepo.findAccountByUsername(Mockito.anyString())).thenReturn(mockaccount);

        Mockito.when(
                bankService.depositCash(anyObject(), Mockito.anyDouble())).thenReturn(true);

        String exampleAccountJson = "{\"username\":\"john\",\"password\":\"abc\"}";

        Mockito.when(accountRepo.save(Mockito.any(Account.class))).thenReturn(mockaccount2);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/account/deposit/20")
                .contentType(MediaType.APPLICATION_JSON)
                .content(exampleAccountJson);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{message:'Deposit Successful'}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    void withdraw() throws Exception {
        Account mockaccount = new Account("john", "abc", 40, "john", "doe");

        Account mockaccount2 = new Account("john", "abc", 20, "john", "doe");

        Mockito.when(
                accountRepo.findAccountByUsername(Mockito.anyString())).thenReturn(mockaccount);

        Mockito.when(
                bankService.withdrawCash(anyObject(), Mockito.anyDouble())).thenReturn(true);

        String exampleAccountJson = "{\"username\":\"john\",\"password\":\"abc\"}";

        Mockito.when(accountRepo.save(Mockito.any(Account.class))).thenReturn(mockaccount2);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/account/withdraw/20")
                .contentType(MediaType.APPLICATION_JSON)
                .content(exampleAccountJson);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{message:'Withdraw successful'}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    void enquiry() throws Exception{
        Account mockaccount = new Account("john", "abc", 40, "john", "doe");

        Mockito.when(
                accountRepo.findAccountByUsername(Mockito.anyString())).thenReturn(mockaccount);

        String exampleAccountJson = "{\"username\":\"john\",\"password\":\"abc\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/account/enquiry")
                .contentType(MediaType.APPLICATION_JSON)
                .content(exampleAccountJson);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{username:john,password:abc,balance:40,firstname:john,lastname:doe}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

}
