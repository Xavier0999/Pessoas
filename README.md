package br.org.Pessoas.controller;


import br.org.Pessoas.PessoasApplication;
import br.org.Pessoas.exception.handler.ErroResponse;
import br.org.Pessoas.model.Pessoa;
import br.org.Pessoas.service.PessoaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpsServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PessoasApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class testController {


    private static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PessoaService pessoaService;


    private Pessoa build(Integer id, String nome, String email, Long telefone) {
        return new Pessoa(id, nome, email, telefone);
    }

    @Test
    void testarAdicionar() throws Exception {
  Pessoa add = build(1, "Gustavooooo", "Gustavoo@gmail.com", 51993111712L);

  doAnswer( invocation -> {
      return add;
  }).when(pessoaService).salvar(eq(add));

        MvcResult mvcResult = mockMvc.perform(post("/pessoa").contentType("application/json")
                .content(MAPPER.writeValueAsString(add))).andExpect(status()
                .is(HttpStatus.CREATED.value())).andReturn();

Pessoa retorno = parseResponse(mvcResult, Pessoa.class);

verify(pessoaService, times(1)).salvar(eq(add));
      assertThat("Nao retornou o Id correto", retorno.getId(), equalTo(1));

    }
//
//
//    @Test
//    void testaIdErrado() throws Exception{
//
//        Pessoa add = build(123, "Gustavooooo", "Gustavoo@gmail.com", 51993111712L);
//
//       MvcResult mvcResult = mockMvc.perform(post("/pessoa")
//               .content("application/json").content(MAPPER.writeValueAsString(add))).andExpect(status()
//               .is(HttpStatus.BAD_REQUEST.value())).andReturn();
//
//        ErroResponse erroResponse = parseResponse(mvcResult, ErroResponse.class);
//        assertThat("Nao retornou a validação correta", erroResponse.getMensagem(),
//                containsString("Id nulo ou invalidado"));
//    }
//
//@Test
//void testaNomeNulo() throws Exception{
//
//        Pessoa add = build(123, null, "Gustavoo@gmail.com", 51993111712L);
//
//        MvcResult mvcResult = mockMvc.perform(post("").content("application/json")
//                        .content(MAPPER.writeValueAsString(add)))
//                .andExpect(status().is(HttpStatus.BAD_REQUEST.value())).andReturn();
//
//        ErroResponse erroResponse = parseResponse(mvcResult, ErroResponse.class);
//        assertThat("Nao retornou a validação correta", erroResponse.getMensagem(),
//                containsString("O nome - Nome nao pode ser nulo"));
//    }
//
//@Test
//void testaEmailNulo() throws Exception{
//
//        MvcResult mvcResult = mockMvc.perform(post("/pessoa").content("application/json")
//                .content(MAPPER.writeValueAsString(build(123, "Gustavooooo", null, 51993111712L))))
//                .andExpect(status().is(HttpStatus.BAD_REQUEST.value())).andReturn();
//
//        ErroResponse erroResponse = parseResponse(mvcResult, ErroResponse.class);
//        assertThat("Nao retornou a validaçao correta", erroResponse.getMensagem()
//                ,containsString("O email - Email nao pode ser nulo"));
//
//
//}



    private static <T> List<T> parseResponseList(MvcResult mockHttpServletResponse, Class<T> clazz) {
        try {
            String contentAsString = mockHttpServletResponse.getResponse().getContentAsString();
            return MAPPER.readValue(contentAsString, MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T parseResponse(MvcResult mockHttpServletResponse, Class<T> clazz) {
        try {
            String contentAsString = mockHttpServletResponse.getResponse().getContentAsString();
            return MAPPER.readValue(contentAsString, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
