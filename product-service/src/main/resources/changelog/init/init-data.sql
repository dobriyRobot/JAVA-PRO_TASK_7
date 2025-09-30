INSERT INTO users (username) VALUES
('ivan_petrov'),
('maria_sidorova'),
('alexey_ivanov'),
('olga_kuznetsova'),
('dmitry_smirnov');

INSERT INTO products (payment_number, type, balance, user_id) VALUES
('PAY-001-2024', 'PAYMENT', 2000.00, 1),
('PAY-002-2024', 'PAYMENT', 2000.00, 1),
('PAY-003-2024', 'CARD', 2000.00, 2),
('PAY-004-2024', 'PAYMENT', 2000.00, 2),
('PAY-005-2024', 'CARD', 2000.00, 3),
('PAY-006-2024', 'CARD', 2000.00, 3),
('PAY-007-2024', 'PAYMENT', 2000.00, 4),
('PAY-008-2024', 'PAYMENT', 2000.00, 4),
('PAY-009-2024', 'CARD', 2000.00, 5),
('PAY-010-2024', 'PAYMENT', 2000.00, 5);