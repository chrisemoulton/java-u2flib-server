/*
 * Copyright 2014 Yubico.
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Use of this source code is governed by a BSD-style
 * license that can be found in the LICENSE file or at
 * https://developers.google.com/open-source/licenses/bsd
 */

package com.yubico.u2f.data.messages;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.yubico.u2f.U2fException;
import com.yubico.u2f.data.DataObject;

import static com.google.common.base.Preconditions.checkNotNull;

public class AuthenticateResponse extends DataObject {

  /** websafe-base64(client data) */
  private final String clientData;

  /** websafe-base64(raw response from U2F device) */
  private final String signatureData;

  /** keyHandle originally passed */
  private final String keyHandle;

  public AuthenticateResponse(String clientData, String signatureData, String keyHandle) {
    this.clientData = checkNotNull(clientData);
    this.signatureData = checkNotNull(signatureData);
    this.keyHandle = checkNotNull(keyHandle);
  }

  public ClientData getClientData() throws U2fException {
    return new ClientData(clientData);
  }

  public String getSignatureData() {
    return signatureData;
  }

  public String getKeyHandle() {
    return keyHandle;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(clientData, signatureData, keyHandle);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AuthenticateResponse other = (AuthenticateResponse) obj;
    if (clientData == null) {
      if (other.clientData != null)
        return false;
    } else if (!clientData.equals(other.clientData))
      return false;
    if (keyHandle == null) {
      if (other.keyHandle != null)
        return false;
    } else if (!keyHandle.equals(other.keyHandle))
      return false;
    if (signatureData == null) {
      if (other.signatureData != null)
        return false;
    } else if (!signatureData.equals(other.signatureData))
      return false;
    return true;
  }

  public static AuthenticateResponse fromJson(String json) {
    Gson gson = new Gson();
    return gson.fromJson(json, AuthenticateResponse.class);
  }
}
