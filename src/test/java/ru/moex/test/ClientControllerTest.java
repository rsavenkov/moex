package ru.moex.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.moex.test.validators.Validators;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Client controller intergrational tests
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        clientRepository.deleteAll();
    }

    /**
     * Test creating of client api in different cases
     *
     * @throws Exception
     */
    @Test
    public void createClient() throws Exception {
        // successfully create with x-Source header = MAIL
        Client client = Client.builder()
                .firstName("first name")
                .email("mail@mail.ru")
                .build();

        ResultActions response = mockMvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-Source", Validators.MAIL.name())
                .content(objectMapper.writeValueAsString(client)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is(client.getFirstName())))
                .andExpect(jsonPath("$.email",
                        is(client.getEmail())));

        // validation error with x-Source header = MAIL
        client.setEmail(null);

        response = mockMvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-Source", Validators.MAIL.name())
                .content(objectMapper.writeValueAsString(client)));

        response.andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(status().reason(containsString("Валидация обязательных полей прошла не успешно")));

        // successfully create with x-Source header = MOBILE
        client = Client.builder()
                .phone("71111111111")
                .build();

        response = mockMvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-Source", Validators.MOBILE.name())
                .content(objectMapper.writeValueAsString(client)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.phone", is(client.getPhone())));

        // validation error with x-Source header = MOBILE
        client.setPhone(null);

        response = mockMvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-Source", Validators.MOBILE.name())
                .content(objectMapper.writeValueAsString(client)));

        response.andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(status().reason(containsString("Валидация обязательных полей прошла не успешно")));

        // successfully create with x-Source header = BANK
        client = Client.builder()
                .bankId(123456L)
                .birthDate(new Date())
                .lastName("Last name")
                .firstName("First name")
                .thirdName("Third name")
                .passport("1111 111111")
                .build();

        response = mockMvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-Source", Validators.BANK.name())
                .content(objectMapper.writeValueAsString(client)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bankId", is(client.getBankId()), Long.class))
//                .andExpect(jsonPath("$.birthDate", is(client.getBirthDate()), Date.class))
                .andExpect(jsonPath("$.firstName", is(client.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(client.getLastName())))
                .andExpect(jsonPath("$.thirdName", is(client.getThirdName())))
                .andExpect(jsonPath("$.passport", is(client.getPassport())));

        // validation error with x-Source header = BANK
        client.setPassport(null);

        response = mockMvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-Source", Validators.BANK.name())
                .content(objectMapper.writeValueAsString(client)));

        response.andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(status().reason(containsString("Валидация обязательных полей прошла не успешно")));

        // x-Source = GOSUSLUGI
        client = Client.builder()
                .bankId(123456L)
                .birthDate(new Date())
                .lastName("Last name")
                .firstName("First name")
                .thirdName("Third name")
                .passport("1111 111111")
                .regAddress("reg address")
                .birthPlace("birth place")
                .phone("71111111111")
                .build();

        response = mockMvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-Source", Validators.GOSUSLUGI.name())
                .content(objectMapper.writeValueAsString(client)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bankId", is(client.getBankId()), Long.class))
                .andExpect(jsonPath("$.firstName", is(client.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(client.getLastName())))
                .andExpect(jsonPath("$.thirdName", is(client.getThirdName())))
                .andExpect(jsonPath("$.passport", is(client.getPassport())))
                .andExpect(jsonPath("$.regAddress", is(client.getRegAddress())))
                .andExpect(jsonPath("$.birthPlace", is(client.getBirthPlace())))
                .andExpect(jsonPath("$.phone", is(client.getPhone())));

        // not passing x-Source header
        response = mockMvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)));

        response.andDo(print())
                .andExpect(status().isNotImplemented())
                .andExpect(status().reason(containsString("Неверное значение заголовка x-Source")));
    }

    /**
     * Test get client by id api
     *
     * @throws Exception
     */
    @Test
    public void getClient() throws Exception {
        // client not found by id
        ResultActions response = mockMvc.perform(get("/client/{id}", 1));
        response.andExpect(status().isNotFound()).andDo(print());

        // client found by id
        Client client = Client.builder()
                .firstName("first name")
                .lastName("last name")
                .thirdName("third name")
                .email("mail@mail.ru")
                .build();
        clientRepository.save(client);

        response = mockMvc.perform(get("/client/{id}", client.getId()));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(client.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(client.getLastName())))
                .andExpect(jsonPath("$.thirdName", is(client.getThirdName())))
                .andExpect(jsonPath("$.email", is(client.getEmail())));
    }

    /**
     * Test find clients by fields api
     *
     * @throws Exception
     */
    @Test
    public void findClients() throws Exception {
        // not passing any field params
        ResultActions response = mockMvc.perform(get("/client/find"));

        response.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason(containsString("Ни один из параметров не передан!")));

        Client client1 = Client.builder()
                .firstName("first name")
                .email("mail@mail.ru")
                .build();
        clientRepository.save(client1);
        Client client2 = Client.builder()
                .firstName("First name")
                .email("mail@mail.ru")
                .build();
        clientRepository.save(client2);
        Client client3 = Client.builder()
                .lastName("Last name")
                .thirdName("Third name")
                .email("mail1@mail.ru")
                .build();
        clientRepository.save(client3);

        // response contains many clients
        response = mockMvc.perform(get("/client/find?email={email}", client1.getEmail()));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)));

        // case sensitive request
        response = mockMvc.perform(get("/client/find?firstName={firstName}", client2.getFirstName()));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(client2.getFirstName())));

        // search by several request params
        response = mockMvc.perform(get("/client/find?emil={email}&lastName={lastName}", client3.getEmail(), client3.getLastName()));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(client3.getFirstName())));

    }

}
