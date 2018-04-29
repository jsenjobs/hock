package contracts

import org.springframework.cloud.contract.spec.Contract

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/27
 */
Contract.make {
    request {
        method 'GET'
        url('/fraudcheck')  {
            queryParameters {
                parameter 'id' : $(regex('[0-9]{10}'))
                parameter 'loanAmount' : 99999
            }
        }
        /*
        body([
                "client.id": $(regex('[0-9]{10}')),
                loanAmount: 99999
        ])
        */
        headers {
            contentType('application/json')
        }
    }
    response {
        status OK()
        body([
                fraudCheckStatus: "FRAUD",
                "rejection.reason": "Amount too high"
        ])
        headers {
            contentType('application/json')
        }
    }
}