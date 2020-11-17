def callAndDeploy(String subscription, String appresourcegroup, String tenantID, String subscriptionID, String clientID, String clientSecret){
    echo "using subscription: ${subscription}"
    
    withCredentials([azureServicePrincipal(subscription)]) {
        echo "AZURE_TENANT_ID: ${tenantID}"
        echo "AZURE SUBSCRIPTION ID: ${subscriptionID}"
        sh "az login --service-principal -u ${clientID} -p ${clientSecret} -t ${tenantID}"
        sh "az account set --subscription ${subscriptionID}"
        sh "az deployment group create --resource-group ${appresourcegroup} --template-file azuredeploy.json --parameters azuredeploy.parameters.json"
    }
}