create database cross_fit;
USE CROSS_FIT;

CREATE TABLE `Member`
(
    `member_num`   BIGINT UNSIGNED NOT NULL auto_increment primary key,
    `member_id`    varchar(100)    NOT NULL unique,
    `member_name`  VARCHAR(50)     NOT NULL,
    `email`        VARCHAR(100)    NOT NULL unique,
    `password`     VARCHAR(255)    NOT NULL,
    `birth`        DATE            NULL,
    `preference`   INT             NULL,
    `invest_score` INT             NULL,
    `reg_Date`     DATETIME        NULL,
    `gender`       VARCHAR(10)     NULL
);

create table member_role
(
    member_role_id bigint unsigned primary key auto_increment not null,
    role           varchar(10)                                not null,
    member_num     bigint unsigned,
    foreign key (member_num) references member (member_num)
);

CREATE TABLE PRODUCT
(
    productID   INT auto_increment primary KEY,
    productType CHAR(1) NOT NULL
);

CREATE TABLE SavingsProduct
(
    productID     INT PRIMARY KEY,
    fin_co_no     VARCHAR(50),
    fin_prdt_cd   VARCHAR(50),
    kor_co_nm     VARCHAR(100),
    fin_prdt_nm   VARCHAR(100),
    dcls_month    VARCHAR(6),
    join_way      TEXT,
    mtrt_int      TEXT,
    spcl_cnd      TEXT,
    join_deny     INT,
    join_member   VARCHAR(100),
    etc_note      TEXT,
    max_limit     VARCHAR(255),
    dcls_strt_day VARCHAR(8),
    dcls_end_day  VARCHAR(8),
    RiskLevel     INT,
    hit           INT DEFAULT 0,
    FOREIGN KEY (ProductID) REFERENCES Product (ProductID)
);

CREATE TABLE SavingProductRates
(
    productID         INT,
    save_trm          INT,
    intr_rate_type    CHAR(1),
    intr_rate_type_nm VARCHAR(20),
    intr_rate         DECIMAL(5, 2),
    intr_rate2        DECIMAL(5, 2),
    rsrv_type         VARCHAR(10),
    rsrv_type_nm      VARCHAR(50),
    PRIMARY KEY (productID, save_trm, rsrv_type),
    FOREIGN KEY (productID) REFERENCES SavingsProduct (productID)
);

CREATE TABLE FundProduct
(
    productID         INT NOT NULL,   -- 금융상품 테이블의 외래키
    company_nm        VARCHAR(100),   -- 운용사명
    product_nm        VARCHAR(255),   -- 상품명
    yield_1           DECIMAL(15, 2), -- 1개월 누적수익률
    yield_3           DECIMAL(15, 2), -- 3개월 누적수익률
    yield_6           DECIMAL(15, 2), -- 6개월 누적수익률
    yield_12          DECIMAL(15, 2), -- 12개월 누적수익률
    RiskLevel         INT,            -- 펀드 등급
    fund_type         VARCHAR(50),    -- 펀드 유형
    advanced_fee      DECIMAL(15, 2), -- 선취수수료
    total_payoff_rate DECIMAL(15, 2), -- 총 보수율
    hit               INT,            -- 조회수
    PRIMARY KEY (ProductID),          -- Primary Key 설정
    FOREIGN KEY (ProductID) REFERENCES Product (ProductID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE BondProduct
(
    productID         INT NOT NULL,  -- 상품ID (Product 테이블의 외래키)
    basDt             VARCHAR(8),    -- 기준일자
    crno              VARCHAR(13),   -- 법인등록번호
    scrsItmsKcd       VARCHAR(4),    -- 유가증권종목코드
    isinCd            VARCHAR(12),   -- ISIN코드
    scrsItmsKcdNm     VARCHAR(50),   -- 유가증권종목코드명
    bondIsurNm        VARCHAR(100),  -- 채권발행인명
    isinCdNm          VARCHAR(100),  -- ISIN코드명
    bondIssuDt        VARCHAR(8),    -- 채권발행일자
    bondIssuAmt       BIGINT,        -- 채권발행금액
    bondIssuCurCd     VARCHAR(3),    -- 채권발행통화코드
    bondIssuCurCdNm   VARCHAR(50),   -- 채권발행통화코드명
    bondExprDt        VARCHAR(8),    -- 채권만기일자
    bondPymtAmt       BIGINT,        -- 채권납입금액
    irtChngDcd        CHAR(1),       -- 금리변동구분코드
    irtChngDcdNm      VARCHAR(50),   -- 금리변동구분코드명
    bondSrfcInrt      DECIMAL(5, 2), -- 채권금리
    bondIntTcd        CHAR(1),       -- 채권이자형구분코드
    bondIntTcdNm      VARCHAR(50),   -- 채권이자형구분코드명
    intPayCyclCtt     VARCHAR(50),   -- 이자지급주기내용
    nxtmCopnDt        VARCHAR(8),    -- 차기표일자
    rbVopnDt          VARCHAR(8),    -- 직전이표일자
    kbpScrsItmsKcdNm  VARCHAR(100),  -- 한국신용평가유가증권종목종류코드명
    niceScrsItmsKcdNm VARCHAR(100),  -- NICE평가정보유가증권종목종류코드명
    fnScrsItmsKcdNm   VARCHAR(100),  -- FN유가증권종목종류코드명
    riskLevel         INT,           -- 위험도
    hit               INT DEFAULT 0, -- 조회수, 기본값 0
    PRIMARY KEY (productID),         -- Primary Key 설정
    FOREIGN KEY (productID) REFERENCES Product (productID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `stock`
(
    `stockCode`  VARCHAR(10)    NOT NULL, -- 주식 코드
    `stockName`  VARCHAR(50)    NULL,     -- 주식 이름
    `dailyPrice` DECIMAL(10, 2) NULL,     -- 일일 가격
    `field`      INT            NULL,     -- 분야 코드
    `itmsNm`     VARCHAR(50)    NULL,     -- 항목 이름
    `mrktCtg`    VARCHAR(50)    NULL,     -- 시장 카테고리
    `clpr`       INT            NULL,     -- 종가
    `vs`         INT            NULL,     -- 등락폭
    `fltRt`      DECIMAL(5, 2)  NULL,     -- 등락률
    `mkp`        INT            NULL,     -- 시가
    `hipr`       INT            NULL,     -- 최고가
    `lopr`       INT            NULL,     -- 최저가
    `trqu`       BIGINT         NULL,     -- 거래량
    `trPrc`      BIGINT         NULL,     -- 거래대금
    `istgStCnt`  BIGINT         NULL,     -- 상장 주식 수
    `mrktTotAmt` BIGINT         NULL,     -- 시장 총액
    PRIMARY KEY (`stockCode`)             -- stockCode를 기본 키로 설정
);

CREATE TABLE `Portfolio`
(
    `portfolioID`    INT             NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `portfolioName`  VARCHAR(100)    NULL,
    `creationDate`   DATETIME        NULL,
    `total`          DECIMAL(15, 2)  NULL,
    `expectedReturn` DECIMAL(5, 2)   NULL,
    `riskLevel`      INT             NULL,
    `memberNum`      BIGINT UNSIGNED NOT NULL
);

CREATE TABLE `PortfolioItem`
(
    `portfolioItemID` INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `portfolioID`     INT           NOT NULL,
    `productID`       INT           NULL,
    `stockCode`       VARCHAR(10)   NULL,
    `amount`          INT           NULL,
    `expectedReturn`  DECIMAL(5, 2) NULL,
    FOREIGN KEY (`portfolioID`) REFERENCES `portfolio` (`portfolioID`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `CartItem`
(
    `cartID`         INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `productID`      INT           NOT NULL,
    `userNum`        INT           NOT NULL,
    `productType`    CHAR(1)       NOT NULL,
    `provider`       VARCHAR(100)  NULL,
    `productName`    VARCHAR(100)  NOT NULL,
    `expectedReturn` DECIMAL(5, 2) NULL,
    `interestRate`   DECIMAL(5, 2) NULL
);

INSERT INTO `CartItem` (`cartID`, `productID`, `memberNum`, `productType`, `provider`, `productName`, `expectedReturn`,
                        `interestRate`)
VALUES (1, 1, 1, 'S', 'Provider A', 'Savings Product 1', 3.50, NULL),
       (2, 12, 2, 'F', 'Provider B', 'Fund Product 1', NULL, 2.75),
       (3, 14, 1, 'B', 'Provider C', 'Bond Product 1', 4.20, NULL),
       (4, 74, 2, 'C', 'Provider D', 'Cash Product 1', NULL, 1.50),
       (5, 165, 1, 'S', 'Provider E', 'Savings Product 2', 2.80, NULL),
       (6, 63, 2, 'F', 'Provider F', 'Fund Product 2', NULL, 3.10),
       (7, 107, 1, 'B', 'Provider G', 'Bond Product 2', 3.95, NULL),
       (8, 8, 2, 'C', 'Provider H', 'Cash Product 2', NULL, 0.85),
       (9, 91, 1, 'S', 'Provider I', 'Savings Product 3', 4.50, NULL),
       (10, 10, 2, 'F', 'Provider J', 'Fund Product 3', NULL, 2.90);

CREATE TABLE `Insight`
(
    `insightID` INT          NOT NULL,
    `type`      VARCHAR(20)  NULL,
    `content`   TEXT         NULL,
    `URL`       VARCHAR(255) NULL
);

CREATE TABLE `AgeGroupProductHits`
(
    `SelectedNum` INT NULL,
    `AgeGroup`    INT NULL,
    `HitCount`    INT NULL,
    `ProductID`   INT NOT NULL
);
