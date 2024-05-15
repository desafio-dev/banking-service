package br.com.bycoders.desafiodev.bankingservice.domains.enums;

public enum RolesEnum {
    ADMIN(1L),
    BASIC(2L);

    long roleId;

    RolesEnum(long roleId) {
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }
}
