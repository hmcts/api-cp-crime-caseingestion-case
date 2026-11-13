# APIM auto-attaches the administrators group to every new product, so the
# first apply fails until that link is imported.
import {
  to = module.product.azurerm_api_management_product_group.access_control_groups["administrators"]
  id = "/subscriptions/bd2864ed-4f3e-45ed-9c6a-8d179674bab1/resourceGroups/rg-sps-platform-sbox/providers/Microsoft.ApiManagement/service/sps-api-mgmt-sbox/products/cp-crime-caseingestion/groups/administrators"
}