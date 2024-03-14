package com.group.oldjavalibraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JavaUserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {

  UserLoanHistory findByBookNameAndIsReturn(String bookName, boolean isReturn);

}
