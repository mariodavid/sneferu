package de.diedavids.sneferu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.web.testsupport.TestContainer;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControllableDataServiceProxyTest {

  private ControllableDataServiceProxy sut;

  @BeforeEach
  void setUp() {
    final TestContainer testContainer = mock(TestContainer.class);
    sut = new ControllableDataServiceProxy(testContainer);
  }


  @Test
  void when_commitWasPerformedByTheEnvironment_then_committedInstanceCanBeRetrievedById() {

    // when:
    final UUID newUserId = UUID.randomUUID();
    final User user = user(newUserId);

    sut.persistCommit(Collections.singleton(user));

    // then:
    assertThat(
        sut.committed(User.class, newUserId)
    ).isEqualTo(user);

  }

  @Test
  void when_commitWasPerformedByTheEnvironment_then_committedInstanceCanBeRetrievedByPredicate() {

    // when:
    final User user = user();
    user.setLogin("userLogin");

    sut.persistCommit(Collections.singleton(user));

    // then:
    assertThat(
        sut.committed(User.class, possibleUser -> possibleUser.getLogin().equals("userLogin"))
        .get()
    ).isEqualTo(user);

  }


  @Test
  void when_commitWasPerformedByTheEnvironment_and__noEntityMatchesThePredicate_thenNothingIsReturned() {

    // when:
    final User user = user();
    user.setLogin("userLogin");

    sut.persistCommit(Collections.singleton(user));

    // then:
    assertThat(
        sut.committed(User.class, possibleUser -> possibleUser.getLogin().equals("notMatching"))
    ).isEqualTo(Optional.empty());

  }

  private User user() {
    return user(UUID.randomUUID());
  }

  private User user(UUID id) {
    final User user = new User();
    user.setId(id);
    return user;
  }
}