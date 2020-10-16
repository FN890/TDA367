package data;

import java.net.InetAddress;

public class Address {

    private final InetAddress address;
    private final int port;

    public Address(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return port == address1.port &&
                address.equals(address1.address);
    }

}
