package ch.shinungo.pejo.repository;

import org.springframework.data.repository.CrudRepository;

import ch.shinungo.pejo.model.Iban;

public interface IbanRepository extends CrudRepository<Iban, Long> {

	public Iban findByIbannumber(String ibannumber);

}
