package com.example;

import com.example.model.User;
import com.example.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class UserServiceTest {

    private UserService userService;
    private static final User IVA = User.of(1, "Iva", "123");
    private static final User CECILIA = User.of(2, "Cecilia", "111");

    UserServiceTest(TestInfo testInfo){
        System.out.println();
    }

    @BeforeAll
    static void init() {
        System.out.println("Before all: ");
    }

    @BeforeEach
    void prepare() {
        System.out.println("Before each: " + this);
        userService = new UserService();
    }

    @Test
    @Order(1)
    @DisplayName("users will be empty if no user add")
    void usersEmptyIfNoUserAdded() {
        System.out.println("Test 1 " + this);
        var users = userService.getAll();
        assertTrue(users.isEmpty(), () -> "User list should be empty");
    }

    @Test
    void userSizeIfUserAdded() {
        userService.add(IVA);
        userService.add(CECILIA);

        var users = userService.getAll();
        assertThat(users).hasSize(2);
//        assertEquals(2,users.size());
    }

    @AfterEach
    void deleteDataFromDatabase() {
        System.out.println("After each: " + this);
    }

    @AfterAll
    static void closeConnectionPool() {
        System.out.println("After all: ");
    }


    @Test
    void loginSuccessIfUserExists() {
        userService.add(IVA);
        Optional<User> maybeUser = userService.login(IVA.getUsername(), IVA.getPassword());
        assertThat(maybeUser).isPresent();
        maybeUser.ifPresent(user -> assertThat(user).isEqualTo(IVA));
//        assertTrue(maybeUser.isPresent());
//       maybeUser.ifPresent(user->assertEquals(IVA,user));
    }

//    @Test
//    @Tag("login")
//    void loginFailIfPasswordIsNotCorrect(){
//         userService.add(IVA);
//
//         var maybeUser=userService.login(IVA.getUsername(),"excel");
//         assertTrue(maybeUser.isEmpty());
//    }

    @Test
    void usersConvertedToMapById() {
        userService.add(IVA);
        userService.add(CECILIA);


        Map<Integer, User> users = userService.getAllConvertedById();
        assertAll(
                () -> assertThat(users).containsKeys(IVA.getId(), CECILIA.getId()),
                () -> assertThat(users).containsValues(IVA, CECILIA)
        );
    }

    @Test
    void throwExceptionIfUsernameOrPasswordIsNull() {
//        try{
        assertThrows(IllegalArgumentException.class, () -> userService.login(null, "excel"));
//            userService.login(null,"excel");
//            fail("login should throw exception on null username");
//        }catch (IllegalArgumentException e){
//            assertTrue(true);
//        }
    }

//    @ParameterizedTest
//    @ArgumentsSources()
    // � ����� ����������
//    @NullSource
//    @EmptySource
//    @ValueSource
//    @NullAndEmptySource
//    @EnumSource
//    @MethodSource("getArgumentsForLoginTest")
//    void loginParameterizedTest(String username,String password,Optional<User>user){
//        userService.add(IVA);
//        userService.add(CECILIA);
//        var maybeUser = userService.login(username, password);
//        assertThat(maybeUser).isEqualTo(user);
//    }

    static Stream<Arguments> getArgumentsForLoginTest(){
        return Stream.of(
                Arguments.of("Iva","123",Optional.of(IVA)),
                Arguments.of("CECILIA","111",Optional.of(CECILIA)),
                Arguments.of("Iva","excel",Optional.empty()),
                Arguments.of("Excel","123",Optional.empty())
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/login-test-data.csv",delimiter = ',',numLinesToSkip = 1)
//    @CsvSource({
//            "Iva","123"
//    })
    void loginParameterizedTest(String username,String password){
        userService.add(IVA);
        userService.add(CECILIA);
        var maybeUser = userService.login(username, password);
        assertThat(maybeUser).isEqualTo(null);
    }


    @Tag("login")
    @Nested
    class LoginTest {
        @Test
        void loginFailIfPasswordIsNotCorrect() {
            userService.add(IVA);

            var maybeUser = userService.login(IVA.getUsername(), "excel");
            assertTrue(maybeUser.isEmpty());
        }
    }
}