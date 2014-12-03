/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.vertx.groovy.core.datagram;
import groovy.transform.CompileStatic
import io.vertx.lang.groovy.InternalHelper
import io.vertx.groovy.core.buffer.Buffer
import io.vertx.groovy.core.net.SocketAddress
/**
 * A received Datagram packet (UDP) which contains the data and information about the sender of the data itself.
 *
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
@CompileStatic
public class DatagramPacket {
  final def io.vertx.core.datagram.DatagramPacket delegate;
  public DatagramPacket(io.vertx.core.datagram.DatagramPacket delegate) {
    this.delegate = delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  /**
   * Returns the {@link io.vertx.core.net.SocketAddress} of the sender that send this {@link io.vertx.core.datagram.DatagramPacket}.
   */
  public SocketAddress sender() {
    def ret= SocketAddress.FACTORY.apply(this.delegate.sender());
    return ret;
  }
  /**
   * Returns the data of the {@link io.vertx.core.datagram.DatagramPacket}
   */
  public Buffer data() {
    def ret= Buffer.FACTORY.apply(this.delegate.data());
    return ret;
  }

  static final java.util.function.Function<io.vertx.core.datagram.DatagramPacket, DatagramPacket> FACTORY = io.vertx.lang.groovy.Factories.createFactory() {
    io.vertx.core.datagram.DatagramPacket arg -> new DatagramPacket(arg);
  };
}