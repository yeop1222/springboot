package study.springboot.advanced_jpa.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import study.springboot.advanced_jpa.data.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	// 주제 키워드
	
	// find...By
	Optional<Product> findByNumber(Long number);
	List<Product> findAllByName(String name);
	Product queryByNumber(Long number);
	
	// exists...By
	boolean existsByNumber(Long number);
	
	// count...By
	long countByName(String name);
	
	// delete...By
	void deleteByNumber(Long number);
	long removeByName(String name);
	
	// ...First<number>..., ...Top<number>...
	List<Product> findFirst5ByName(String name);
	List<Product> findTop10ByName(String name);
	
	// 조건자 키워드
	
	// Is = Equals = (생략)
	Product findByNumberIs(Long number);
	Product findByNumberEquals(Long number);
	
	// IsNot = Not
	Product findByNumberIsNot(Long number);
	Product findByNumberNot(Long number);
	
	// (Is)Null, (Is)NotNull
	List<Product> findByUpdatedAtNull();
	List<Product> findByUpdatedAtIsNull();
	List<Product> findByUpdatedAtNotNull();
	List<Product> findByUpdatedAtIsNotNull();
	
	/*
	// (Is)True, (Is)False
	Product findByisActiveTrue();
	Product findByisActiveIsTrue();
	Product findByisActiveFalse();
	Product findByisActiveIsFalse();
	*/
	
	// And, Or
	Product findByNumberAndName(Long number, String name);
	Product findByNumberOrName(Long number, String name);
	
	// (Is)GreaterThan, (Is)LessThan, (Is)Between
	List<Product> findByPriceIsGreaterThan(Long price);
	List<Product> findByPriceGreaterThan(Long price);
	List<Product> findByPriceGreaterThanEqual(Long price);
	List<Product> findByPriceIsLessThan(Long price);
	List<Product> findByPriceLessThan(Long price);
	List<Product> findByPriceLessThanEqual(Long price);
	List<Product> findByPriceIsBetween(Long lowPrice, Long highPrice);
	List<Product> findByPriceBetween(Long lowPrice, Long highPrice);
	
	// (Is)StartingWith(=StartsWith), (Is)EndingWith(=EndsWith)
	// (Is)Containing(=Contains), (Is)Like
	List<Product> findByNameLike(String name);
	List<Product> findByNameIsLike(String name);

	List<Product> findByNameContains(String name);
	List<Product> findByNameContaining(String name);
	List<Product> findByNameIsContaining(String name);
	
	List<Product> findByNameStartsWith(String name);
	List<Product> findByNameStartingWith(String name);
	List<Product> findByNameIsStartingWith(String name);
	
	List<Product> findByNameEndsWith(String name);
	List<Product> findByNameEndingWith(String name);
	List<Product> findByNameIsEndingWith(String name);
	
	// 정렬
	
	// OrderBy + Asc, Desc
	List<Product> findByNameOrderByNumberAsc(String name);
	List<Product> findByNameOrderByNumberDesc(String name);
	// 조건 여러개일때 그냥 나열
	List<Product> findByNameOrderByPriceAscStockDesc(String name);
	// 조건 여러개일때 이름 너무 길어지면 기준을 매개변수로 받아들임
	List<Product> findByName(String name, Sort sort);
	
	// 페이징 처리
	
	Page<Product> findByName(String name, Pageable pageable);
	
	//@Query annotation 사용하기
	
	@Query("select p from Product p where p.name = ?1")
	List<Product> findByName(String name);
	@Query("select p from Product p where p.name = :name")
	List<Product> findByNameParam(@Param("name") String name);
	@Query("select p.name, p.price, p.stock from Product p where p.name = :name")
	List<Object[]> findByNameParam2(@Param("name") String name);
}
