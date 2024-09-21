import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import './ProductDetails.css';

const ProductDetails = () => {
    const { id } = useParams();
    const [product, setProduct] = useState(null);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchProduct = async () => {
            setLoading(true);
            try {
                const response = await axios.get(`http://localhost:8080/products/${id}`);
                setProduct(response.data);
            } catch (error) {
                console.error("상품 상세 정보를 불러오는 중 오류가 발생했습니다.", error);
            } finally {
                setLoading(false);
            }
        };

        fetchProduct();
    }, [id]);

    return (
        <div className="product-details-container">
            {loading ? (
                <p>로딩 중...</p>
            ) : product ? (
                <div className="product-details">
                    <img src={product.img} alt={product.name} className="product-image" />
                    <div className="product-info">
                        <h1 className="product-name">{product.name}</h1>
                        <p className="product-price">{product.price}</p>
                        <p className="product-comment">{product.comment}</p>
                    </div>
                </div>
            ) : (
                <p>상품을 찾을 수 없습니다.</p>
            )}
        </div>
    );
};

export default ProductDetails;
