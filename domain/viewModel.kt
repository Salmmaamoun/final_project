private val viewModel: SearchLexicalViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: AyahAdapter // Create your RecyclerView adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView
        adapter = AyahAdapter() // Create your RecyclerView adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Set up search button click listener
        binding.searchButton.setOnClickListener {
            val searchTerm = binding.searchEditText.text.toString()
            viewModel.searchLexical(searchTerm)
        }

        // Observe search results
        lifecycleScope.launchWhenStarted {
            viewModel.searchResults.collect { ayahs ->
                adapter.submitList(ayahs)
            }
        }
    }