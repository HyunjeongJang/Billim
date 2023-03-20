DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
                          `member_id`	int	NOT NULL	COMMENT '회원번호',
                          `id`	varchar(100)	NOT NULL	COMMENT '회원ID',
                          `password`	varchar(200)	NOT NULL	COMMENT '비밀번호',
                          `name`	varchar(100)	NOT NULL	COMMENT '회원이름',
                          `nickname`	varchar(100)	NOT NULL	COMMENT '닉네임',
                          `address`	varchar(100)	NOT NULL	COMMENT '회원주소',
                          `email`	varchar(200)	NOT NULL	COMMENT '회원이메일',
                          `grade`	varchar(10)	NOT NULL	COMMENT '회원등급',
                          `created_at`	timestamp	NOT NULL	COMMENT '가입일자',
                          `updated_at`	timestamp	NOT NULL	COMMENT '접속일자'
);

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
                           `product_id`	int	NOT NULL	COMMENT '상품번호',
                           `category_id`	int	NOT NULL	COMMENT '카테고리번호',
                           `member_id`	int	NOT NULL	COMMENT '판매회원번호',
                           `product_name`	varchar(100)	NOT NULL	COMMENT '상품명',
                           `detail`	varchar(3000)	NOT NULL	COMMENT '상품설명',
                           `price`	int	NOT NULL	COMMENT '대여요금',
                           `area`	varchar(100)	NOT NULL	COMMENT '거래지역',
                           `created_at`	timestamp	NOT NULL	COMMENT '등록일자',
                           `updated_at`	timestamp	NOT NULL	COMMENT '수정일자',
                           `trade_method`	varchar(10)	NOT NULL	COMMENT '거래방법'
);

DROP TABLE IF EXISTS `product_category`;

CREATE TABLE `product_category` (
                                    `category_id`	int	NOT NULL	COMMENT '카테고리번호',
                                    `category_name`	varchar(100)	NOT NULL	COMMENT '카테고리명'
);

DROP TABLE IF EXISTS `attachment`;

CREATE TABLE `attachment` (
                              `file_id`	int	NOT NULL	COMMENT '파일번호',
                              `member_id`	int	NOT NULL	COMMENT '업로더번호',
                              `file_name`	varchar(200)	NOT NULL	COMMENT '파일이름',
                              `created_at`	timestamp	NOT NULL	COMMENT '생성일자'
);

DROP TABLE IF EXISTS `interest_list`;

CREATE TABLE `interest_list` (
                                 `interest_id`	int	NOT NULL	COMMENT '관심상품번호',
                                 `product_id`	int	NOT NULL	COMMENT '상품번호',
                                 `member_id`	int	NOT NULL	COMMENT '회원번호'
);

DROP TABLE IF EXISTS `block`;

CREATE TABLE `block` (
                         `member_id`	int	NOT NULL	COMMENT '회원번호',
                         `target_id`	int	NOT NULL	COMMENT '차단대상번호',
                         `created_at`	timestamp	NOT NULL	COMMENT '생성일자'
);

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
                         `share_id`	int	NOT NULL	COMMENT '대여번호',
                         `product_id`	int	NOT NULL	COMMENT '상품번호',
                         `member_id`	int	NOT NULL	COMMENT '구매회원번호',
                         `status`	varchar(10)	NOT NULL	COMMENT '대여상태',
                         `start_at`	timestamp	NOT NULL	COMMENT '시작일',
                         `end_at`	timestamp	NOT NULL	COMMENT '종료일',
                         `address`	varchar(200)	NULL	COMMENT '주소',
                         `phone`	varchar(100)	NULL	COMMENT '연락처',
                         `created_at`	timestamp	NOT NULL	COMMENT '주문일자',
                         `canceled_at`	timestamp	NULL	COMMENT '취소일자'
);

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
                           `payment_id`	int	NOT NULL	COMMENT '결제번호',
                           `share_id`	int	NOT NULL	COMMENT '대여번호',
                           `coupon_id`	int	NULL	COMMENT '쿠폰번호',
                           `point`	int	NULL	COMMENT '적립금',
                           `merchant_uid`	varchar(500)	NULL	COMMENT '카드결제ID',
                           `total_amount`	int	NOT NULL	COMMENT '총금액',
                           `created_at`	timestamp	NOT NULL	COMMENT '결제일자',
                           `status`	boolean	NOT NULL	COMMENT '결제여부',
                           `trade_method`	varchar(10)	NOT NULL	COMMENT '거래 방법'
);

DROP TABLE IF EXISTS `saved_point`;

CREATE TABLE `saved_point` (
                               `point_no`	int	NOT NULL	COMMENT '적립금번호',
                               `member_no`	int	NOT NULL	COMMENT '회원번호',
                               `amount`	int	NOT NULL	COMMENT '적립금액',
                               `expired_at`	timestamp	NOT NULL	COMMENT '소멸일자',
                               `created_at`	timestamp	NOT NULL	COMMENT '적립일자',
                               `updated_at`	timestamp	NOT NULL	COMMENT '사용일자'
);

DROP TABLE IF EXISTS `coupon_issue`;

CREATE TABLE `coupon_issue` (
                                `issue_id`	int	NOT NULL	COMMENT '쿠폰번호',
                                `member_id`	int	NOT NULL	COMMENT '회원번호',
                                `coupon_id`	int	NOT NULL	COMMENT '쿠폰 번호',
                                `created_at`	timestamp	NOT NULL	COMMENT '생성일자',
                                `expired_at`	timestamp	NOT NULL	COMMENT '소멸일자',
                                `status`	varchar(10)	NOT NULL	COMMENT '쿠폰상태'
);

DROP TABLE IF EXISTS `chat_room`;

CREATE TABLE `chat_room` (
                             `chat_room_id`	int	NOT NULL	COMMENT '채팅방번호',
                             `product_id`	int	NOT NULL	COMMENT '상품번호',
                             `member_id`	int	NOT NULL	COMMENT '구매회원번호',
                             `created_at`	timestamp	NOT NULL	COMMENT '생성일자',
                             `updated_at`	timestamp	NOT NULL	COMMENT '업데이트일자'
);

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
                                `notify_id`	int	NOT NULL	COMMENT '알림번호',
                                `member_id`	int	NOT NULL	COMMENT '수신회원번호',
                                `type`	varchar(50)	NOT NULL	COMMENT '알림타입',
                                `is_read`	boolean	NOT NULL	COMMENT '읽음여부',
                                `is_delete`	boolean	NOT NULL	COMMENT '삭제여부',
                                `content`	varchar(100)	NOT NULL	COMMENT '알림내용',
                                `created_at`	timestamp	NOT NULL	COMMENT '생성일자'
);

DROP TABLE IF EXISTS `chat_message`;

CREATE TABLE `chat_message` (
                                `message_id`	int	NOT NULL	COMMENT '메세지번호',
                                `chat_room_id`	int	NOT NULL	COMMENT '채팅방번호',
                                `member_id`	int	NOT NULL	COMMENT '발신회원번호',
                                `message`	varchar(2000)	NOT NULL	COMMENT '채팅메세지',
                                `is_read`	boolean	NOT NULL	COMMENT '읽음여부',
                                `created_at`	timestamp	NOT NULL	COMMENT '생성일자'
);

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
                          `review_id`	int	NOT NULL	COMMENT '리뷰번호',
                          `product_id`	int	NOT NULL	COMMENT '상품번호',
                          `member_id`	int	NOT NULL	COMMENT '구매회원번호',
                          `content`	varchar(1000)	NOT NULL	COMMENT '리뷰내용',
                          `star_rating`	int	NOT NULL	COMMENT '별점',
                          `created_at`	timestamp	NOT NULL	COMMENT '작성일자',
                          `updated_at`	timestamp	NOT NULL	COMMENT '업데이트일자',
                          `status`	varchar(10)	NOT NULL	COMMENT '삭제여부'
);

DROP TABLE IF EXISTS `image_product`;

CREATE TABLE `image_product` (
                                 `file_id`	int	NOT NULL	COMMENT '파일번호',
                                 `product_id`	int	NOT NULL	COMMENT '상품번호'
);

DROP TABLE IF EXISTS `image_chat`;

CREATE TABLE `image_chat` (
                              `file_id`	int	NOT NULL	COMMENT '파일번호',
                              `message_id`	int	NOT NULL	COMMENT '메세지번호'
);

DROP TABLE IF EXISTS `saved_point_rate`;

CREATE TABLE `saved_point_rate` (
                                    `grade`	varchar(10)	NOT NULL	COMMENT '회원등급',
                                    `rate`	int	NOT NULL	COMMENT '적립률'
);

DROP TABLE IF EXISTS `coupon`;

CREATE TABLE `coupon` (
                          `coupon_id`	int	NOT NULL	COMMENT '쿠폰 번호',
                          `name`	varchar(200)	NOT NULL	COMMENT '쿠폰이름',
                          `rate`	int	NOT NULL	COMMENT '차감률',
                          `limit_date`	int	NOT NULL	COMMENT '유효기간'
);

DROP TABLE IF EXISTS `image_profile`;

CREATE TABLE `image_profile` (
                                 `file_id`	int	NOT NULL	COMMENT '파일번호',
                                 `member_id`	int	NOT NULL	COMMENT '회원번호'
);

ALTER TABLE `member` ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
                                                             `member_id`
    );

ALTER TABLE `product` ADD CONSTRAINT `PK_PRODUCT` PRIMARY KEY (
                                                               `product_id`
    );

ALTER TABLE `product_category` ADD CONSTRAINT `PK_PRODUCT_CATEGORY` PRIMARY KEY (
                                                                                 `category_id`
    );

ALTER TABLE `attachment` ADD CONSTRAINT `PK_ATTACHMENT` PRIMARY KEY (
                                                                     `file_id`
    );

ALTER TABLE `interest_list` ADD CONSTRAINT `PK_INTEREST_LIST` PRIMARY KEY (
                                                                           `interest_id`
    );

ALTER TABLE `block` ADD CONSTRAINT `PK_BLOCK` PRIMARY KEY (
                                                           `member_id`
    );

ALTER TABLE `order` ADD CONSTRAINT `PK_ORDER` PRIMARY KEY (
                                                           `share_id`
    );

ALTER TABLE `payment` ADD CONSTRAINT `PK_PAYMENT` PRIMARY KEY (
                                                               `payment_id`
    );

ALTER TABLE `saved_point` ADD CONSTRAINT `PK_SAVED_POINT` PRIMARY KEY (
                                                                       `point_no`
    );

ALTER TABLE `coupon_issue` ADD CONSTRAINT `PK_COUPON_ISSUE` PRIMARY KEY (
                                                                         `issue_id`
    );

ALTER TABLE `chat_room` ADD CONSTRAINT `PK_CHAT_ROOM` PRIMARY KEY (
                                                                   `chat_room_id`
    );

ALTER TABLE `notification` ADD CONSTRAINT `PK_NOTIFICATION` PRIMARY KEY (
                                                                         `notify_id`
    );

ALTER TABLE `chat_message` ADD CONSTRAINT `PK_CHAT_MESSAGE` PRIMARY KEY (
                                                                         `message_id`
    );

ALTER TABLE `review` ADD CONSTRAINT `PK_REVIEW` PRIMARY KEY (
                                                             `review_id`
    );

ALTER TABLE `image_product` ADD CONSTRAINT `PK_IMAGE_PRODUCT` PRIMARY KEY (
                                                                           `file_id`,
                                                                           `product_id`
    );

ALTER TABLE `image_chat` ADD CONSTRAINT `PK_IMAGE_CHAT` PRIMARY KEY (
                                                                     `file_id`,
                                                                     `message_id`
    );

ALTER TABLE `saved_point_rate` ADD CONSTRAINT `PK_SAVED_POINT_RATE` PRIMARY KEY (
                                                                                 `grade`
    );

ALTER TABLE `coupon` ADD CONSTRAINT `PK_COUPON` PRIMARY KEY (
                                                             `coupon_id`
    );

ALTER TABLE `image_profile` ADD CONSTRAINT `PK_IMAGE_PROFILE` PRIMARY KEY (
                                                                           `file_id`,
                                                                           `member_id`
    );
