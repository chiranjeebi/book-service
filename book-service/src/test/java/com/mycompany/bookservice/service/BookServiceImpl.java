package com.mycompany.bookservice.service;

import com.mycompany.bookservice.controller.BookController;
import com.mycompany.bookservice.dto.BookDTO;
import com.mycompany.bookservice.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BookServiceImpl {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;



    @Test
    void testUpdateBookPrice(){
        BookDTO bookDTO=new BookDTO();
        bookDTO.setPricePerQty(99.05);


        Mockito.when(bookService.updateBookPrice(Mockito.any(), Mockito.anyLong())).thenReturn(bookDTO);
        ResponseEntity<BookDTO> responseEntity = bookController.updateBookPrice(bookDTO, 1L);

        assertEquals(99.05, responseEntity.getBody().getPricePerQty());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

}
