package org.progressoft.dynamicparserspting.connection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = History.class, idClass = String.class)
public interface HistoryRepo extends JpaRepository<History, String> {}

