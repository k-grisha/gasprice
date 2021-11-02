package gr.ether.gasprice.repository

import gr.ether.gasprice.model.GasPrice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface GasPriceRepository : JpaRepository<GasPrice, Long> {


    @Query(
        "select max(id) as id, CAST(AVG(gas_price) as int) as gas_price, date_trunc(:period, datetime) as datetime " +
                "from gas_price " +
                "group by 3 " +
                "having AVG(gas_price) < :maxPrice " +
                "order by 3 DESC limit :limit ",
        nativeQuery = true
    )
    fun fetchAll(@Param("limit") limit: Int,
                 @Param("period") period: String,
                 @Param("maxPrice") maxPrice: Int
    ): Iterable<GasPrice>
}