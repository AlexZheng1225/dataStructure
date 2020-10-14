package SegmentTree;

/**
 * @Author Alex Zheng
 * @Date 2020/10/8 11:00
 * @Annotation 融合器
 */
public interface Merger<E> {
    E merge(E a, E b);
}
