package com.example.demo;

import com.example.demo.dao.BookDao;
import com.example.demo.dao.BorrowDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Borrow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	private BorrowDao borrowDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private UserDao userDao;

	@Test
	public void contextLoads() {
		/*Borrow borrow = new Borrow();
		borrow.setId(UUID.randomUUID().toString());
		borrow.setBookId(bookDao.getOne("1"));
		borrow.setUserId(userDao.getOne("1"));
		borrowDao.save(borrow);*/
		List <Borrow> all = borrowDao.findAll();

		all.forEach(System.out::println);
	}

}
