api_mgmt_rg   = "rg-sps-platform-sbox"
api_mgmt_name = "sps-api-mgmt-sbox"

apim_product = {
  name                          = "cp-crime-caseingestion"
  subscription_required         = true
  subscriptions_limit           = 20
  approval_required             = false
  published                     = true
  product_access_control_groups = ["developers", "administrators", "guests"]
}

entra_tenant_id = "d44f885c-4fac-47bf-afde-d7d861ec4d7b"
entra_client_id = "30288840-e345-4543-99ee-f9253d789339"

apis = {
  case = {
    # The source spec is modular ($ref to paths/ and schemas/). Terraform reads it with file(),
    # so terraform-infra.yaml bundles it into one file at this path before Terraform runs.
    openapi_spec_path = "../src/main/resources/openapi/openapi.yml"
    display_name      = "Crime Case Ingestion Case API (cpci)"
    path              = "amp/cpci"                    # TODO: confirm APIM path
    service_host      = "devamp01-appgw.dev.nl.cjscp" # TODO: confirm backend host for service-cp-crime-caseingestion-case
    service_path      = "/cpci"                       # TODO: confirm backend path
    revision          = "1"
    required_roles    = ["app.read"] # TODO: POST /cases is a write; confirm whether a write role is needed
    # Pre-filled Authorization and Accept boxes in the portal's Try it (see apis.tf).
    try_it_auth_headers = true
  }
}

# Lets clients get a token with their own credentials in the sandbox portal (see entra-token.tf).
entra_token_helper = {
  app_insights_logger_name = "sps-api-mgmt-sbox-logger"
}
