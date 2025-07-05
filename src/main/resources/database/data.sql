INSERT INTO users (id, name) VALUES
                                 ('u001', 'Nguyễn Văn A'),
                                 ('u002', 'Trần Thị B');

INSERT INTO diaries (id, date, title, location, activity, amount, feeling, rating, notes) VALUES
                                                                                              ('d001', '2025-07-01', 'Ngày đầu viết nhật ký', 'Hà Nội', 'Đi học, ăn trưa cùng bạn', 150000, 'Vui vẻ', 8, 'Trời nắng đẹp, hơi nóng 🥵'),
                                                                                              ('d002', '2025-07-02', 'Cuối tuần thư giãn', 'Nhà', 'Ngủ nướng, đọc sách', 0, 'Thư giãn', 9, 'Không ra ngoài, nghỉ ngơi');

INSERT INTO user_diaries (user_id, diary_id) VALUES
                                                 ('u001', 'd001'),
                                                 ('u001', 'd002'),
                                                 ('u002', 'd002');

INSERT INTO diary_participants (diary_id, participant_name) VALUES
                                                                ('d001', 'Minh'),
                                                                ('d001', 'Linh'),
                                                                ('d002', 'Hùng');
