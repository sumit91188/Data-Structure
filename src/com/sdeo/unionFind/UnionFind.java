package com.sdeo.unionFind;

/**
 * @author Sumit Deo
 */
public class UnionFind {

    private int numElms;
    private int[] componentSize;
    private int[] parentElm;
    private int numComponents;

    public UnionFind(int numElms) {
        this.numElms = numElms;
        numComponents = numElms;

        componentSize = new int[numElms];
        parentElm = new int[numElms];

        for (int i = 0; i < numElms; i++) {
            componentSize[i] = 1;
            parentElm[i] = i;
        }
    }

    public int size() {
        return numElms;
    }

    public int getNumComponents() {
        return numComponents;
    }

    public int find(int elm) {
        int rootElm = elm;

        while (rootElm != parentElm[rootElm]) {
            rootElm = parentElm[rootElm];
        }

        while (rootElm != elm) {
            int parent = parentElm[elm];
            parentElm[elm] = rootElm;
            elm = parent;
        }

        return rootElm;
    }

    public boolean isConnected(int elm1, int elm2) {
        return find(elm1) == find(elm2);
    }

    public int getComponentSize(int elm) {
        return componentSize[find(elm)];
    }

    public void unify(int elm1, int elm2) {

        if (isConnected(elm1, elm2)) {
            return;
        }

        int rootElm1 = find(elm1);
        int rootElm2 = find(elm2);

        if (componentSize[rootElm1] < componentSize[rootElm2]) {
            componentSize[rootElm2] += componentSize[rootElm1];
            parentElm[rootElm1] = rootElm2;
        }
        else {
            componentSize[rootElm1] += componentSize[rootElm2];
            parentElm[rootElm2] = rootElm1;
        }

        numComponents--;
    }
}
