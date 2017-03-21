USE bookstore;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `books` VALUES (NULL, 'Thinking In Java', 'Bruce Eckel', '../images/think_in_java.jpg', '108','4', '100','Thinking in Java is a book about the Java programming language, written by Bruce Eckel and first published in 1998. Prentice Hall published the 4th edition of the work in 2006. The book represents a print version of Eckel’s “Hands-on Java” seminar.');
INSERT INTO `books` VALUES (NULL,'Computer Systems',"Randal E. Bryant, David R. O'Hallaron",'../images/computer_system.jpg', '80','5','100', "Few students studying computer science or computer engineering will ever have the opportunity to build a computer system. On the other hand, most students will be required to use and program computers on a near daily basis. Computer Systems: A Programmer’s Perspective introduces the important and enduring concepts that underlie computer systems by showing how these ideas affect the correctness, performance, and utility of application programs. The text's hands-on approach (including a comprehensive set of labs) helps students understand the “under-the-hood” operation of a modern computer system and prepares them for future courses in systems topics such as compilers, computer architecture, operating systems, and networking.");
INSERT INTO `books` VALUES (NULL,'Introduction to Algorithms','Thomas H.Cormen','../images/introduction_to_algorithms.jpg','102','4','200', "Introduction to Algorithms is a book by Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein.");
INSERT INTO `books` VALUES (NULL,'Database System Concepts','Abraham Silberschatz','../images/database_system_concepts.jpg','58.5' ,'5' ,'95' , "Database System Concepts, by Abraham Silberschatz and Hank Korth, is a classic textbook on database system. It is often called the sailboat book.");
INSERT INTO `books` VALUES (NULL,'Programming: Principles and Practice Using C++','Bjarne Stroustrup','../images/programming_principle_and_practice_using_cpp.jpg', '64.90','5' ,'205' , 'An Introduction to Programming by the Inventor of C++');
INSERT INTO `books` VALUES (NULL,'Data Structures and Algorithm Analysis in C++','Mark Allen Weiss','../images/data_structures_and_algorithm_analysis.jpg','432','3','45','Data Structures and Algorithm Analysis in C++ is an advanced algorithms book that bridges the gap between traditional CS2 and Algorithms Analysis courses.');

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (NULL, 'root', '123456');
INSERT INTO `users` VALUES (NULL, 'dynamicheart', '123456');

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `orders` VALUES (NULL, '1', '2','Ordered');
INSERT INTO `orders` VALUES (NULL, '2', '5','Completed');
INSERT INTO `orders` VALUES (NULL, '2', '3','Canceled');
