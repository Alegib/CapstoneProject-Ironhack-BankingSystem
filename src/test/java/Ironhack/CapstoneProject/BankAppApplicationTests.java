package Ironhack.CapstoneProject;

import Ironhack.CapstoneProject.DTO.AccountDTO;
import Ironhack.CapstoneProject.DTO.AccountHolderDTO;
import Ironhack.CapstoneProject.DTO.TransactionDTO;
import Ironhack.CapstoneProject.models.Accounts.Checking;
import Ironhack.CapstoneProject.models.Accounts.Savings;
import Ironhack.CapstoneProject.models.Enums.PaymentMode;
import Ironhack.CapstoneProject.models.Enums.Status;
import Ironhack.CapstoneProject.models.Transactions.Money;
import Ironhack.CapstoneProject.models.Transactions.Transaction;
import Ironhack.CapstoneProject.models.Users.AccountHolder;
import Ironhack.CapstoneProject.models.Users.Address.Address;
import Ironhack.CapstoneProject.models.Users.Admin;
import Ironhack.CapstoneProject.models.Users.Role;
import Ironhack.CapstoneProject.models.repositories.*;
import com.google.gson.*;
import com.google.gson.internal.GsonBuildConfig;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Locale;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BankAppApplicationTests {
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AccountHolderRepository accountHolderRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	ThirdPartyRepository thirdPartyRepository;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	LoginRepository loginRepository;


	private MockMvc mockMvc;

	GsonBuilder gsonBuilder = new GsonBuilder()
			.registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
			.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());

	Gson gson = gsonBuilder.setPrettyPrinting().create();


	private final ObjectMapper objectMapper = new ObjectMapper();



	@BeforeEach
	void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		Address address = new Address("Carrer Galicia", "Barcelona", "España", "12", "08054");
		AccountHolder accountHolder1 = new AccountHolder("Alessio", 32, LocalDate.of(1990, 7, 19), "Alex", "1234", "alessio@gmail.com", "345 54 34 345", address);
		Checking checking = new Checking(accountHolder1, new Money(BigDecimal.valueOf(2000)), Status.ACTIVE);

		accountRepository.save(checking);
		accountHolderRepository.save(accountHolder1);
	}
	@AfterEach
	void tearDown(){
		loginRepository.deleteAll();
		transactionRepository.deleteAll();
		roleRepository.deleteAll();
		accountRepository.deleteAll();
		thirdPartyRepository.deleteAll();
		userRepository.deleteAll();
		accountRepository.deleteAll();
		accountHolderRepository.deleteAll();

	}


	@Test
	public void createAccountHolder() throws Exception {


		Address address = new Address("Carrer Rocafort", "Barcelona", "España", "20", "08015");
		AccountHolderDTO accountHolderDTO = new AccountHolderDTO("Gianni", 20, LocalDate.now(), "gianni", "1234", "gianni@gmail.com", "345 54 34 345", address);
		String body = gson.toJson(accountHolderDTO);

		MvcResult mvcResult = mockMvc.perform(post("/create-accountHolder").content(body).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();
		assertTrue(mvcResult.getResponse().getContentAsString().contains("gianni@gmail.com"));

	}

	@Test
	void checkfindThirdPartyId() throws Exception {

		MvcResult mvcResult = mockMvc.perform(get("/find-thirdParty/123"))
				.andExpect(status().isNotFound())
				.andReturn();

		assertTrue(mvcResult.getResolvedException()
				.getMessage()
				.contains("Third Party Account not found."));
	}
	@Test
	void checkfindAllTransactionByAccountId() throws Exception {

		MvcResult mvcResult = mockMvc.perform(get("/all-transactions/1"))
				.andExpect(status().isNotFound())
				.andReturn();

		assertTrue(mvcResult.getResolvedException()
				.getMessage()
				.contains("No records found."));
	}

	@Test
	public void checkCreateAdmin() throws Exception {

		Admin admin = new Admin("admin", "admin", "admin");
		Role role = new Role("ADMIN", admin);
		String body = objectMapper.writeValueAsString(admin);


		MvcResult mvcResult = mockMvc.perform(post("/create-admin").content(body)

						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		assertTrue(mvcResult.getResponse().getContentAsString().contains("admin"));

	}

	@Test
	public void checkFindAllAccounts() throws Exception {

		MvcResult mvcResult = mockMvc.perform(get("/all-accounts"))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void checkDeleteAccount() throws Exception {

		AccountDTO accountDTO = new AccountDTO(1L);
		String body = gson.toJson(accountDTO);
		System.out.println(body);
		MvcResult mvcResult = mockMvc.perform(delete("/delete-account").content(body).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted()).andReturn();

	}

	@Test
	public void checkInstantTransaction() throws Exception {


		Address address = new Address("Carrer Rocafort", "Barcelona", "España", "20", "08015");
		AccountHolder accountHolder = new AccountHolder("Gianni", 20, LocalDate.now(), "alexx", "1234", "alessio@gmail.com", "345 54 34 345", address);
		Savings savings = new Savings(accountHolder, new Money(BigDecimal.valueOf(1000)), Status.ACTIVE, Savings.DEFAULT_INTEREST_RATE);


		Address address2 = new Address("Carrer Galicia", "Barcelona", "España", "12", "08054");
		AccountHolder accountHolder1 = new AccountHolder("Alessio", 32, LocalDate.of(1990, 7, 19), "Ale", "1234", "alessio@gmail.com", "345 54 34 345", address2);
		Checking checking = new Checking(accountHolder1, new Money(BigDecimal.valueOf(2000)), Status.ACTIVE);

		accountRepository.saveAll(List.of(savings, checking));

		TransactionDTO transaction = new TransactionDTO(new Money(BigDecimal.valueOf(200)), PaymentMode.INSTANT,  savings.getId(), checking.getSecretKey(), "Gift");

		String body = gson.toJson(transaction);

		accountRepository.save(savings);

		MvcResult mvcResult = mockMvc.perform(post("/transaction/send").content(body).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted()).andReturn();
		assertEquals(1800, checking.getBalance().getAmount());

	}
}


