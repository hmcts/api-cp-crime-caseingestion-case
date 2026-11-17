# Infrastructure (retired)

This repo moved to [hmcts/api-cp-civil-caseingestion-case](https://github.com/hmcts/api-cp-civil-caseingestion-case),
which now owns the sandbox APIM product `cp-civil-caseingestion` (API path `amp/cpci`).

The resource definitions were removed from here so that Terraform destroys everything this repo created in
`sps-api-mgmt-sbox`: the `cp-crime-caseingestion` product, the `amp/cic` API and its policy, and the
`cpci-entra-token` helper API. Only the backend and provider (`init.tf`) and the variables remain, so the
pipeline can still run that destroy.
