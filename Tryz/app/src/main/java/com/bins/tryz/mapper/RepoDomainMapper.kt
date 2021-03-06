package  com.bins.tryz.mapper

import com.bins.domain.entity.DataEntity
import com.bins.domain.entity.RepoEntity
import com.bins.tryz.entity.Data
import com.bins.tryz.entity.SquireRepo


class DomainToPresentationMapper {

    fun mapTo(data: DataEntity<List<RepoEntity>>): Data<List<SquireRepo>> {
        return when (data) {
            is DataEntity.SUCCESS -> Data.SUCCESS(data.data?.let { mapToList(it) })
            is DataEntity.ERROR -> Data.ERROR(error =  data.error)
        }
    }

    private fun mapToList(responses: List<RepoEntity>?)
            : List<SquireRepo> = responses?.map { mapToPresentationEntity(it) } ?: emptyList()

    private fun mapToPresentationEntity(response: RepoEntity): SquireRepo = SquireRepo(
        id= response.id,
        name= response.name,
        full_name= response.full_name,
        description= response.description,
        avatar_url= response.avatar_url
    )
}
