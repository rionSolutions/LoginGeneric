package com.orionsolution.auth.service;

import com.orionsolution.auth.model.CredentialsDTO;
import com.orionsolution.auth.model.ResourcesPolicyDataDTO;

public interface AuthenticatorService {

    ResourcesPolicyDataDTO authenticate(CredentialsDTO credentials);
}
