package com.example.umc10th.domain.user.entity.mapping;

import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.user.entity.FoodCategory;
import com.example.umc10th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "food_choice")
public class FoodChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_choice_id")
    private Long foodChoiceId;

    /*
     * FK: user_id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /*
     * FK: food_category_id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_category_id")
    private FoodCategory foodCategory;
}
