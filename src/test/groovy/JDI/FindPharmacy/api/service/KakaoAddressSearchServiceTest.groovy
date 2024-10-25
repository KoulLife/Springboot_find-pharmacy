package JDI.FindPharmacy.api.service

import JDI.FindPharmacy.AbstractIntegrationContainerBaseTest
import JDI.FindPharmacy.api.dto.KakaoApiResponseDto
import org.springframework.beans.factory.annotation.Autowired

class KakaoAddressSearchServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    KakaoAddressSearchService kakaoAddressSearchService

    def setup(){

    }

    def "address 파라미터 값이 null 이면, requestAddressSearch 메소드는 null을 리턴"() {
        given:
        String address = null

        when:
        def result = kakaoAddressSearchService.requestAddressSearch(address)

        then:
        result == null
    }

    def "주소값이 valid 하다면, requestAddressSearch 메소드는 정상적으로 document를 반환"() {
        given:
        def address = "서울 성북구 종암로 10길"

        when:
        def result = kakaoAddressSearchService.requestAddressSearch(address)

        then:
        result.documentDtoList.size() > 0
        result.metaDto.totalCount > 0
        result.documentDtoList.get(0).addressName != null

    }

}