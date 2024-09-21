import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import './ProductList.css';

// 한 페이지당 표시할 상품의 수
const ITEMS_PER_PAGE = 10;
const PAGE_GROUP_SIZE = 10; // 한 번에 표시할 페이지 번호 수

const ProductList = () => {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);

    const goToPage = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    const fetchProducts = async (page) => {
        setLoading(true);
        try {
            const response = await axios.get('http://localhost:8080/products', {
                params: {
                    page: page - 1,
                    size: ITEMS_PER_PAGE,
                },
            });
            setProducts(response.data.content);
            setTotalPages(response.data.totalPages);
        } catch (error) {
            console.error('상품 데이터를 불러오는 중 오류가 발생했습니다.', error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchProducts(currentPage);
    }, [currentPage]);

    const generatePageNumbers = () => {
        const pages = [];
        const currentGroup = Math.floor((currentPage - 1) / PAGE_GROUP_SIZE);
        const startPage = currentGroup * PAGE_GROUP_SIZE + 1;
        const endPage = Math.min(startPage + PAGE_GROUP_SIZE - 1, totalPages);

        for (let i = startPage; i <= endPage; i++) {
            pages.push(i);
        }

        return pages;
    };

    return (
        <div className="product-list-container">
            <h1>상품 리스트</h1>
            {loading ? (
                <p>로딩 중...</p>
            ) : (
                <div>
                    <ul>
                        {products.map((product) => (
                            <li key={product.id}>
                                <Link to={`/products/${product.id}`} className="product-link">
                                    <div className="product-item">
                                        <img className="product-img" src={product.img} alt={product.name} />
                                        <div className="product-info">
                                            <p className="product-name">{product.name}</p>
                                            <p className="product-price">{product.price}원</p>
                                        </div>
                                    </div>
                                </Link>
                            </li>
                        ))}
                    </ul>

                    {/* 페이징 */}
                    <div className="pagination">
                        <button onClick={() => goToPage(1)} disabled={currentPage === 1}>
                            &lt;&lt; 처음
                        </button>
                        <button onClick={() => goToPage(currentPage - 1)} disabled={currentPage === 1}>
                            &lt; 이전
                        </button>
                        {generatePageNumbers().map((page) => (
                            <button
                                key={page}
                                onClick={() => goToPage(page)}
                                disabled={currentPage === page}
                            >
                                {page}
                            </button>
                        ))}
                        <button onClick={() => goToPage(currentPage + 1)} disabled={currentPage === totalPages}>
                            다음 &gt;
                        </button>
                        <button onClick={() => goToPage(totalPages)} disabled={currentPage === totalPages}>
                            끝 &gt;&gt;
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default ProductList;
