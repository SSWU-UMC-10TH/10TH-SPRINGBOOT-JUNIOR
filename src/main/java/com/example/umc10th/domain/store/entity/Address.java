package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.store.enums.Emd;
import com.example.umc10th.domain.store.enums.Sido;
import com.example.umc10th.domain.store.enums.Sigungu;
import com.example.umc10th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "store")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "zipcode", nullable = false, length = 100)
    private String zipcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "sido", nullable = false)
    private Sido sido;

    @Enumerated(EnumType.STRING)
    @Column(name = "sigungu", nullable = false)
    private Sigungu sigungu;

    @Enumerated(EnumType.STRING)
    @Column(name = "emd", nullable = false)
    private Emd emd;

    @Column(name = "address_line", length = 100)
    private String addressLine;

    /*
     * FK: user_id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /*
     * FK: store_id
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
