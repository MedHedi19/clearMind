<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20250419164603 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql(<<<'SQL'
            CREATE TABLE messenger_messages (id BIGINT AUTO_INCREMENT NOT NULL, body LONGTEXT NOT NULL, headers LONGTEXT NOT NULL, queue_name VARCHAR(190) NOT NULL, created_at DATETIME NOT NULL COMMENT '(DC2Type:datetime_immutable)', available_at DATETIME NOT NULL COMMENT '(DC2Type:datetime_immutable)', delivered_at DATETIME DEFAULT NULL COMMENT '(DC2Type:datetime_immutable)', INDEX IDX_75EA56E0FB7336F0 (queue_name), INDEX IDX_75EA56E0E3BD61CE (available_at), INDEX IDX_75EA56E016BA31DB (delivered_at), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            DROP INDEX user_id ON adhd_responses
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE adhd_responses CHANGE id id INT NOT NULL, CHANGE category category VARCHAR(50) NOT NULL, CHANGE pregnancy_complicated pregnancy_complicated TINYINT(1) NOT NULL, CHANGE premature_birth premature_birth TINYINT(1) NOT NULL, CHANGE birth_complications birth_complications TINYINT(1) NOT NULL, CHANGE learning_delay learning_delay TINYINT(1) NOT NULL, CHANGE hyperactive hyperactive TINYINT(1) NOT NULL, CHANGE sleep_issues sleep_issues TINYINT(1) NOT NULL, CHANGE family_tensions family_tensions TINYINT(1) NOT NULL, CHANGE family_adhd family_adhd TINYINT(1) NOT NULL, CHANGE screen_time screen_time TINYINT(1) NOT NULL, CHANGE concentration_issue concentration_issue TINYINT(1) NOT NULL, CHANGE loses_items loses_items TINYINT(1) NOT NULL, CHANGE impatience impatience TINYINT(1) NOT NULL, CHANGE impulsive impulsive TINYINT(1) NOT NULL, CHANGE emotional_issues emotional_issues TINYINT(1) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE admin CHANGE admin_id admin_id INT NOT NULL, CHANGE full_name full_name VARCHAR(100) NOT NULL, CHANGE image image VARCHAR(500) NOT NULL, CHANGE gender gender VARCHAR(50) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE appointment CHANGE id id INT NOT NULL, CHANGE patient_id patient_id BIGINT NOT NULL, CHANGE diagnosis diagnosis VARCHAR(255) NOT NULL, CHANGE treatment treatment VARCHAR(255) NOT NULL, CHANGE date_modify date_modify DATE NOT NULL, CHANGE date_delete date_delete DATE NOT NULL, CHANGE specialized specialized VARCHAR(100) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE doctor CHANGE id id INT NOT NULL, CHANGE gender gender VARCHAR(100) NOT NULL, CHANGE mobile_number mobile_number BIGINT NOT NULL, CHANGE specialized specialized VARCHAR(100) NOT NULL, CHANGE address address VARCHAR(200) NOT NULL, CHANGE image image VARCHAR(500) NOT NULL, CHANGE modify_date modify_date DATE NOT NULL, CHANGE delete_date delete_date DATE NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE patient CHANGE id id INT NOT NULL, CHANGE full_name full_name VARCHAR(100) NOT NULL, CHANGE gender gender VARCHAR(100) NOT NULL, CHANGE mobile_number mobile_number BIGINT NOT NULL, CHANGE address address VARCHAR(200) NOT NULL, CHANGE image image VARCHAR(500) NOT NULL, CHANGE description description VARCHAR(200) NOT NULL, CHANGE diagnosis diagnosis VARCHAR(200) NOT NULL, CHANGE treatment treatment VARCHAR(200) NOT NULL, CHANGE doctor doctor VARCHAR(100) NOT NULL, CHANGE specialized specialized VARCHAR(100) NOT NULL, CHANGE date_modify date_modify DATE NOT NULL, CHANGE date_delete date_delete DATE NOT NULL, CHANGE first_time first_time VARCHAR(50) NOT NULL, CHANGE status_pay status_pay VARCHAR(255) NOT NULL, CHANGE price price VARCHAR(100) NOT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE payment CHANGE Id id INT NOT NULL, CHANGE doctor doctor VARCHAR(250) NOT NULL, CHANGE date_pay date_pay DATE NOT NULL
        SQL);
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql(<<<'SQL'
            DROP TABLE messenger_messages
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE adhd_responses CHANGE id id INT AUTO_INCREMENT NOT NULL, CHANGE category category VARCHAR(50) DEFAULT NULL, CHANGE pregnancy_complicated pregnancy_complicated TINYINT(1) DEFAULT NULL, CHANGE premature_birth premature_birth TINYINT(1) DEFAULT NULL, CHANGE birth_complications birth_complications TINYINT(1) DEFAULT NULL, CHANGE learning_delay learning_delay TINYINT(1) DEFAULT NULL, CHANGE hyperactive hyperactive TINYINT(1) DEFAULT NULL, CHANGE sleep_issues sleep_issues TINYINT(1) DEFAULT NULL, CHANGE family_tensions family_tensions TINYINT(1) DEFAULT NULL, CHANGE family_adhd family_adhd TINYINT(1) DEFAULT NULL, CHANGE screen_time screen_time TINYINT(1) DEFAULT NULL, CHANGE concentration_issue concentration_issue TINYINT(1) DEFAULT NULL, CHANGE loses_items loses_items TINYINT(1) DEFAULT NULL, CHANGE impatience impatience TINYINT(1) DEFAULT NULL, CHANGE impulsive impulsive TINYINT(1) DEFAULT NULL, CHANGE emotional_issues emotional_issues TINYINT(1) DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            CREATE INDEX user_id ON adhd_responses (user_id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE admin CHANGE admin_id admin_id INT AUTO_INCREMENT NOT NULL, CHANGE full_name full_name VARCHAR(100) DEFAULT NULL, CHANGE image image VARCHAR(500) DEFAULT NULL, CHANGE gender gender VARCHAR(50) DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE appointment CHANGE id id INT AUTO_INCREMENT NOT NULL, CHANGE patient_id patient_id BIGINT DEFAULT NULL, CHANGE diagnosis diagnosis VARCHAR(255) DEFAULT NULL, CHANGE treatment treatment VARCHAR(255) DEFAULT NULL, CHANGE date_modify date_modify DATE DEFAULT NULL, CHANGE date_delete date_delete DATE DEFAULT NULL, CHANGE specialized specialized VARCHAR(100) DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE doctor CHANGE id id INT AUTO_INCREMENT NOT NULL, CHANGE gender gender VARCHAR(100) DEFAULT NULL, CHANGE mobile_number mobile_number BIGINT DEFAULT NULL, CHANGE specialized specialized VARCHAR(100) DEFAULT NULL, CHANGE address address VARCHAR(200) DEFAULT NULL, CHANGE image image VARCHAR(500) DEFAULT NULL, CHANGE modify_date modify_date DATE DEFAULT NULL, CHANGE delete_date delete_date DATE DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE patient CHANGE id id INT AUTO_INCREMENT NOT NULL, CHANGE full_name full_name VARCHAR(100) DEFAULT NULL, CHANGE gender gender VARCHAR(100) DEFAULT NULL, CHANGE mobile_number mobile_number BIGINT DEFAULT NULL, CHANGE address address VARCHAR(200) DEFAULT NULL, CHANGE image image VARCHAR(500) DEFAULT NULL, CHANGE description description VARCHAR(200) DEFAULT NULL, CHANGE diagnosis diagnosis VARCHAR(200) DEFAULT NULL, CHANGE treatment treatment VARCHAR(200) DEFAULT NULL, CHANGE doctor doctor VARCHAR(100) DEFAULT NULL, CHANGE specialized specialized VARCHAR(100) DEFAULT NULL, CHANGE date_modify date_modify DATE DEFAULT NULL, CHANGE date_delete date_delete DATE DEFAULT NULL, CHANGE first_time first_time VARCHAR(50) DEFAULT NULL, CHANGE status_pay status_pay VARCHAR(255) DEFAULT NULL, CHANGE price price VARCHAR(100) DEFAULT NULL
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE payment CHANGE id Id INT AUTO_INCREMENT NOT NULL, CHANGE doctor doctor VARCHAR(250) DEFAULT NULL, CHANGE date_pay date_pay DATE DEFAULT NULL
        SQL);
    }
}
